package com.juanma.blogmvvm.presentation.screens.login
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.juanma.blogmvvm.presentation.screens.login.components.LoginBottomBar
import com.juanma.blogmvvm.presentation.screens.login.components.LoginContent
import com.juanma.blogmvvm.presentation.ui.theme.BlogMVVMTheme

@Composable
fun LoginScreen(navController: NavHostController) {
    Scaffold(
        topBar = {},
        content = {
                  LoginContent()
        },
        bottomBar = {
            LoginBottomBar(navController)
        }
    )
}