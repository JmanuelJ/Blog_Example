package com.juanma.blogmvvm.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.juanma.blogmvvm.presentation.screens.my_posts.MyPostsScreen
import com.juanma.blogmvvm.presentation.screens.posts.PostsScreen
import com.juanma.blogmvvm.presentation.screens.profile.ProfileScreen
import com.juanma.blogmvvm.presentation.ui.theme.Blue100

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Post.route,
    ){
        composable(route= HomeBottomBarScreen.Post.route){
            PostsScreen(navController)
        }
        composable(route= HomeBottomBarScreen.MyPosts.route){
            MyPostsScreen(navController)
        }
        composable(route= HomeBottomBarScreen.Profile.route){
            ProfileScreen(navController)
        }
        detailsNavGraph(navController)
    }
}

sealed class HomeBottomBarScreen(
    val route: String,
    var title: String,
    val icon: ImageVector,
    val color: Color
){
    object Post: HomeBottomBarScreen(
        route = "post_list",
        title = "Posts",
        icon = Icons.Default.List,
        color = Blue100
    )

    object MyPosts: HomeBottomBarScreen(
        route = "my_posts",
        title = "Mis Posts",
        icon = Icons.Outlined.List,
        color = Blue100
    )

    object Profile: HomeBottomBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person,
        color = Blue100
    )
}