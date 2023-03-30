package com.juanma.blogmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.juanma.blogmvvm.presentation.screens.home.HomeScreen
import com.juanma.blogmvvm.presentation.screens.login.LoginScreen
import com.juanma.blogmvvm.presentation.screens.profile.ProfileScreen
import com.juanma.blogmvvm.presentation.screens.profile_update.ProfileEditScreen
import com.juanma.blogmvvm.presentation.screens.signUp.SignUpScreen

@Composable
fun RootNavGraph(navController: NavHostController){
    
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ){

        authNavGraph(navController= navController)
        composable(
            route= Graph.HOME){
            HomeScreen()
        }

        }
    }

