package com.juanma.blogmvvm.presentation.screens.update_post

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.presentation.components.DefaultButton
import com.juanma.blogmvvm.presentation.components.DefaultTopBar
import com.juanma.blogmvvm.presentation.screens.update_post.components.*
import com.juanma.blogmvvm.presentation.screens.update_post.components.UpdatePostContent

@Composable
fun UpdatePostScreen(navController: NavHostController, post: String, viewModel: UpdatePostViewModel = hiltViewModel()){
    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "Editar Post",
                     upAvailable = true,
                     navController = navController
                 )
        },
        content = {
            UpdatePostContent()
        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Actualizar",
                onClick = { viewModel.onUpdatePost()  })
        }
    )
    UpdatePost()
}