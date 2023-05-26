package com.juanma.blogmvvm.presentation.screens.my_posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.presentation.components.ProgresBar
import com.juanma.blogmvvm.presentation.screens.my_posts.MyPostsViewModel

@Composable
fun GetPostsByIdUser(navController: NavHostController, viewModel: MyPostsViewModel = hiltViewModel()){
    when(val resposne = viewModel.postsResponse){
        //Mostrar que se esta realizando la peticion y todavia esta en proceso
        Response.Loading -> {
            ProgresBar()
        }
        is Response.Success -> {
            MyPostsContent(navController = navController, posts = resposne.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current,resposne.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
    }
}