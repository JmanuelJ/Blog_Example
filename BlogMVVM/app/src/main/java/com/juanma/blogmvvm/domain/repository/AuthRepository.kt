package com.juanma.blogmvvm.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.model.User

interface AuthRepository {
    val currentUser: FirebaseUser?

   suspend fun login(email: String, password: String): Response<FirebaseUser>
   suspend fun signUp(user: User): Response<FirebaseUser>
   fun logout()
}