package com.juanma.blogmvvm.domain.repository

import com.juanma.blogmvvm.domain.model.Post
import com.juanma.blogmvvm.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostsRepository {
    fun getPosts(): Flow<Response<List<Post>>>
    fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>>
    suspend fun create(post: Post, file: File): Response<Boolean>
    suspend fun update(post: Post, file: File?): Response<Boolean>
    suspend fun delete(idPost: String): Response<Boolean>

}