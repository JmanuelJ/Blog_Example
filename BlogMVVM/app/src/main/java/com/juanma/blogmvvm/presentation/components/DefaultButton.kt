package com.juanma.blogmvvm.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juanma.blogmvvm.presentation.ui.theme.Blue500

@Composable
fun DefaultButton(
    modifier: Modifier,
    text: String,
    onClick: ()-> Unit,
    color: Color = Blue500,
    icon: ImageVector = Icons.Default.ArrowForward,
    enabled: Boolean = true
){
    Column() {
        Button(
            modifier = modifier,
            onClick = { onClick()},
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            enabled = enabled
        ) {
            Icon(
                imageVector = icon,
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text)
        }
    }

}