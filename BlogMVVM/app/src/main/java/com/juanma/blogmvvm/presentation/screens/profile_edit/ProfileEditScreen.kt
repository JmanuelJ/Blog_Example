package com.juanma.blogmvvm.presentation.screens.profile_edit

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.presentation.components.DefaultTopBar
import com.juanma.blogmvvm.presentation.screens.profile.components.ProfileContent
import com.juanma.blogmvvm.presentation.screens.profile_edit.components.ProfileEditContent
import com.juanma.blogmvvm.presentation.screens.signUp.components.SignupContent

@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    user: String
){
    Log.d("ProfileEditScreen", "Usuario: $user")
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileEditContent(navController)
        },
        bottomBar = {}
    )
}