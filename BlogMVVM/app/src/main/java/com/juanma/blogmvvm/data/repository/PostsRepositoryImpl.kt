package com.juanma.blogmvvm.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.juanma.blogmvvm.core.Constants.POSTS
import com.juanma.blogmvvm.core.Constants.USERS
import com.juanma.blogmvvm.domain.model.Post
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostsRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference
    ): PostsRepository {

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow{
        val snapshotListener = postsRef.addSnapshotListener{ snapshot, e ->
            GlobalScope.launch (Dispatchers.IO){
                val postsResponse = if (snapshot != null){
                    val posts = snapshot.toObjects(Post::class.java)
                    val idUserArray = ArrayList<String>()

                    posts.forEach{ post->
                        idUserArray.add(post.idUser)
                    }

                    val idUsersList = idUserArray.toSet().toList() //Elementos sin repetir

                    idUsersList.map { id->
                       async {
                           val user = usersRef.document(id).get().await().toObject(User::class.java)!!
                           posts.forEach{post->
                               if(post.idUser == id){
                                   post.user = user
                               }
                           }
                       }
                    }.forEach{
                        it.await()
                    }
                    Response.Success(posts)
                } else{
                    Response.Failure(e)
                }
                trySend(postsResponse)
            }
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.whereEqualTo("idUser", idUser).addSnapshotListener{ snapshot, e ->
            val postsResponse = if (snapshot != null){
                val posts = snapshot.toObjects(Post::class.java)
                snapshot.documents.forEachIndexed { index,  document ->
                    posts[index].id = document.id
                }
                Response.Success(posts)
            }
             else{
                    Response.Failure(e)
             }
            trySend(postsResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try{
            //image
            val fromFile = Uri.fromFile(file)
            val ref = storagePostsRef.child(file.name)
            val uploadtask = ref.putFile(fromFile).await()
            val uri = ref.downloadUrl.await()
            //Data
            post.image = uri.toString()
            postsRef.add(post).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(post: Post, file: File?): Response<Boolean> {
        return try{
            //image
            if(file != null){
                val fromFile = Uri.fromFile(file)
                val ref = storagePostsRef.child(file.name)
                val uploadtask = ref.putFile(fromFile).await()
                val uri = ref.downloadUrl.await()
                post.image = uri.toString()
            }
            val map: MutableMap<String, Any> = HashMap()
            map["name"] = post.name
            map["description"] = post.description
            map["image"] = post.image
            map["category"] = post.category
            //Data
            postsRef.document(post.id).update(map).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun delete(idPost: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).delete().await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

}