package com.juanma.blogmvvm.domain.use_cases.users

import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.repository.UsersRepository
import javax.inject.Inject

class Update@Inject constructor(private val repository: UsersRepository){

    suspend operator fun invoke(user: User) = repository.update(user)
}