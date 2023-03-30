package com.juanma.blogmvvm.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.juanma.blogmvvm.presentation.screens.profile_update.ProfileEditScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController){
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileUpdate.route
    ){
        composable(
            route= DetailsScreen.ProfileUpdate.route,
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

sealed class DetailsScreen(val route: String){
    object ProfileUpdate: DetailsScreen("profile/update/{user}"){
        fun passUser(user: String) = "profile/update/$user"
    }
}