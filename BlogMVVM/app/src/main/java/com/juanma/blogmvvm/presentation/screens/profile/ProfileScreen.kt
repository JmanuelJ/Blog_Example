package com.juanma.blogmvvm.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.presentation.components.DefaultButton
import com.juanma.blogmvvm.presentation.navigation.AppScreen
import com.juanma.blogmvvm.presentation.screens.profile.components.ProfileContent

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()){
    Scaffold(
        topBar = {},
        content = {
            ProfileContent(navController,viewModel)
        },
        bottomBar = {}
    )
}