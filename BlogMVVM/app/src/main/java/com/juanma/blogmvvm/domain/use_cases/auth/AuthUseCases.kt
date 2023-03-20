package com.juanma.blogmvvm.domain.use_cases.auth

data class AuthUseCases (

    val getCurrentUser: GetCurrentUser,
    val login: Login,
    val logout: Logout,
    val signUp: SignUp
)