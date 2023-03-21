package com.juanma.blogmvvm.domain.repository

import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>
    fun getUserById(id: String): Flow<User>
}