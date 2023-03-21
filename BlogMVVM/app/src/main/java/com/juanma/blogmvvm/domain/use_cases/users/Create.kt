package com.juanma.blogmvvm.domain.use_cases.users

import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.create(user)
}