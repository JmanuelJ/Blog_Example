package com.juanma.blogmvvm.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.juanma.blogmvvm.presentation.screens.login.LoginScreen
import com.juanma.blogmvvm.presentation.screens.profile.ProfileScreen
import com.juanma.blogmvvm.presentation.screens.profile_update.ProfileEditScreen
import com.juanma.blogmvvm.presentation.screens.signUp.SignUpScreen

@Composable
fun AppNavigation(navController: NavHostController){
    
    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    ){
        composable(route = AppScreen.Login.route){
            LoginScreen(navController)
        }
        composable(route = AppScreen.Signup.route){
            SignUpScreen(navController)
        }
        composable(route= AppScreen.Profile.route){
            ProfileScreen(navController)
        }
        composable(
            route= AppScreen.ProfileEdit.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("user")?.let {
                ProfileEditScreen(navController, user = it)
            }
        }
    }
}