package com.juanma.blogmvvm.presentation.screens.signUp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.juanma.blogmvvm.presentation.components.DefaultTopBar
import com.juanma.blogmvvm.presentation.screens.login.LoginScreen
import com.juanma.blogmvvm.presentation.screens.signUp.components.SignupContent
import com.juanma.blogmvvm.presentation.ui.theme.BlogMVVMTheme


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
}
