package com.juanma.blogmvvm.presentation.screens.new_post

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.presentation.components.DefaultButton
import com.juanma.blogmvvm.presentation.components.DefaultTopBar
import com.juanma.blogmvvm.presentation.screens.new_post.components.NewPost
import com.juanma.blogmvvm.presentation.screens.new_post.components.NewPostContent

@Composable
fun NewPostScreen(navController: NavHostController, viewModel: NewPostViewModel = hiltViewModel()){
    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "Nuevo Post",
                     upAvailable = true,
                     navController = navController
                 )
        },
        content = {
            NewPostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "PUBLICAR",
                onClick = { viewModel.onNewPost()  })
        }
    )
    NewPost()
}