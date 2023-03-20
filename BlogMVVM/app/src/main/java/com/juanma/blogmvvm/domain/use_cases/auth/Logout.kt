package com.juanma.blogmvvm.domain.use_cases.auth

import com.juanma.blogmvvm.domain.repository.AuthRepository
import javax.inject.Inject

class Logout  @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.logout()
}