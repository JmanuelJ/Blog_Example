package com.juanma.blogmvvm.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.storage.StorageReference
import com.juanma.blogmvvm.core.Constants
import com.juanma.blogmvvm.core.Constants.USERS
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(USERS) private val userRef: CollectionReference,
    @Named(USERS)private val storageUsersRef: StorageReference
): UsersRepository {

    override suspend fun create(user: User): Response<Boolean> {
        return try{
            user.password = ""
            userRef.document(user.id).set(user).await()
            Response.Success(true)
        }catch(e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try{
            val map: MutableMap<String, Any> = HashMap()
            map["username"] = user.username
            map["image"] = user.image
            userRef.document(user.id).update(map).await()
            Response.Success(true)
        }catch(e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try{
            val fromFile = Uri.fromFile(file)
            val ref = storageUsersRef.child(file.name)
            val uploadtask = ref.putFile(fromFile).await()
            val uri = ref.downloadUrl.await()
            return Response.Success(uri.toString())
        }catch(e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val snapshotListener = userRef.document(id).addSnapshotListener{snapshot, e ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }
}