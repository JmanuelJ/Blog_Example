package com.juanma.blogmvvm.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val userRef: CollectionReference): UsersRepository {

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