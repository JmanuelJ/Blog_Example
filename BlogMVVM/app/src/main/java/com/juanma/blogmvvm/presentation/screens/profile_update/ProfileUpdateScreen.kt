package com.juanma.blogmvvm.presentation.screens.profile_update

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.presentation.components.DefaultTopBar
import com.juanma.blogmvvm.presentation.screens.profile_update.components.ProfileUpdate
import com.juanma.blogmvvm.presentation.screens.profile_update.components.ProfileUpdateContent
import com.juanma.blogmvvm.presentation.screens.profile_update.components.SaveImage

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
            ProfileUpdateContent(navController)
        },
        bottomBar = {}
    )
    SaveImage()
    ProfileUpdate()
}