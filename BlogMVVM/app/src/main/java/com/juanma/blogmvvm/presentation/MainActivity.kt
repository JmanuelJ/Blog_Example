package com.juanma.blogmvvm.presentation

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.juanma.blogmvvm.presentation.navigation.AppNavigation
import com.juanma.blogmvvm.presentation.screens.login.LoginScreen
import com.juanma.blogmvvm.presentation.ui.theme.BlogMVVMTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlogMVVMTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navController = rememberNavController()
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}