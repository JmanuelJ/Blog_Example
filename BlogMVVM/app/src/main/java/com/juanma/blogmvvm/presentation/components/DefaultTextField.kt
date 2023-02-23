package com.juanma.blogmvvm.presentation.components

import android.inputmethodservice.ExtractEditText
import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType =KeyboardType.Text,
    hideText: Boolean = false
){
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {onValueChange(it)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = {
            Text( label)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = Color.White
            )
        },
        visualTransformation = if(hideText) PasswordVisualTransformation() else VisualTransformation.None
    )
}