package com.juanma.blogmvvm.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.presentation.components.ProgresBar
import com.juanma.blogmvvm.presentation.navigation.Graph
import com.juanma.blogmvvm.presentation.screens.login.LoginViewModel

@Composable
fun Login (navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()){
    when(val loginResposne = viewModel.loginResponse){
        //Mostrar que se esta realizando la peticion y todavia esta en proceso
        Response.Loading -> {
            ProgresBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                navController.navigate(route = Graph.HOME){
                    popUpTo(Graph.AUTHENTICATION) { inclusive = true}
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginResposne.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
    }
}