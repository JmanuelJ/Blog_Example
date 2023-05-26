package com.juanma.blogmvvm.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.juanma.blogmvvm.presentation.ui.theme.Darkgray500
import androidx.compose.ui.Modifier

@Composable
fun ProgresBar(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        CircularProgressIndicator(
            color = Darkgray500
        )
    }
}