package com.juanma.blogmvvm.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.presentation.components.ProgresBar
import com.juanma.blogmvvm.presentation.navigation.Graph
import com.juanma.blogmvvm.presentation.screens.posts.PostsViewModel

@Composable
fun GetPosts(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()){
    when(val resposne = viewModel.postsResponse){
        //Mostrar que se esta realizando la peticion y todavia esta en proceso
        Response.Loading -> {
            ProgresBar()
        }
        is Response.Success -> {
            PostsContent(navController = navController, posts = resposne.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current,resposne.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
    }
}