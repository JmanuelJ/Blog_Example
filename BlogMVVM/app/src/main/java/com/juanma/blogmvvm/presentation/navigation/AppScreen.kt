package com.juanma.blogmvvm.presentation.navigation

sealed class AppScreen(val route: String) {
    object Login: AppScreen("login")
    object Signup: AppScreen("signup")
    object Profile: AppScreen("profile")
    object ProfileEdit: AppScreen("profile/edit/{user}"){
        fun passUser(user: String) = "profile/edit/$user"
    }
}
