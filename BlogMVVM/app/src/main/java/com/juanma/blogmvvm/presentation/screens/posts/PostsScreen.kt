package com.juanma.blogmvvm.presentation.screens.posts

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.presentation.screens.posts.components.GetPosts

@Composable
fun PostsScreen (navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()){
    Scaffold(
        content = {
                GetPosts(navController)
        }
    )
}