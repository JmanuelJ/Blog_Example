package com.juanma.blogmvvm.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.presentation.components.ProgresBar
import com.juanma.blogmvvm.presentation.navigation.AppScreen
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
                navController.navigate(route = AppScreen.Profile.route){
                    popUpTo(AppScreen.Login.route) { inclusive = true}
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, loginResposne.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
    }
}