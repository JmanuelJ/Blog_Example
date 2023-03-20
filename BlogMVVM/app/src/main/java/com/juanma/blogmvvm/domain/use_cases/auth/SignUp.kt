package com.juanma.blogmvvm.domain.use_cases.auth

import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user : User) = repository .signUp(user)
}