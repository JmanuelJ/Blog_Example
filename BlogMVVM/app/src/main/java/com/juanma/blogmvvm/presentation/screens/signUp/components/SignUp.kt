package com.juanma.blogmvvm.presentation.screens.signUp.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.presentation.components.ProgresBar
import com.juanma.blogmvvm.presentation.navigation.AuthScreen
import com.juanma.blogmvvm.presentation.navigation.Graph
import com.juanma.blogmvvm.presentation.screens.signUp.SignupViewModel

@Composable
fun SignUp(navController: NavHostController, viewModel: SignupViewModel = hiltViewModel()){
    when(val sigupResponse = viewModel.signupResponse){
        Response.Loading -> {
           ProgresBar()
        }
        is Response.Success -> {
            LaunchedEffect(Unit){
                viewModel.createUser()
                navController.popBackStack(Graph.AUTHENTICATION, true)
                navController.navigate( route = Graph.HOME){
                    popUpTo(AuthScreen.Signup.route)
                }
            }
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, sigupResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
    }
}