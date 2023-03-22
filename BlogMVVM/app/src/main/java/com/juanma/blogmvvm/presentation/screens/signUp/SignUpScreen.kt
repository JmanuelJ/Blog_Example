package com.juanma.blogmvvm.presentation.screens.signUp

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.presentation.components.DefaultTopBar
import com.juanma.blogmvvm.presentation.screens.signUp.components.SignUp
import com.juanma.blogmvvm.presentation.screens.signUp.components.SignupContent


@Composable
fun SignUpScreen(navController: NavHostController){
    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "Nuevo Ususario",
                     upAvailable = true,
                     navController = navController
                 )
        },
        content = {
                  SignupContent(navController)
        },
        bottomBar = {}
    )
    SignUp(navController = navController)
}
