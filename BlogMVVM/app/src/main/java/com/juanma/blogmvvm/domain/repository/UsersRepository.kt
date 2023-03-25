package com.juanma.blogmvvm.domain.repository

import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User):Response<Boolean>
    suspend fun saveImage(file: File): Response<String>
    fun getUserById(id: String): Flow<User>
}