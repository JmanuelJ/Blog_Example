package com.juanma.blogmvvm.presentation.screens.profile_update.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.presentation.components.ProgresBar
import com.juanma.blogmvvm.presentation.screens.profile_update.ProfileUpdateViewModel

@Composable
fun SaveImage(viewModel: ProfileUpdateViewModel = hiltViewModel()){
    when(val response = viewModel.saveImageResponse){
        Response.Loading ->{
            ProgresBar()
        }
        is Response.Success ->{
            viewModel.onUpdate(response.data)
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido",Toast.LENGTH_LONG).show()
        }
    }
}