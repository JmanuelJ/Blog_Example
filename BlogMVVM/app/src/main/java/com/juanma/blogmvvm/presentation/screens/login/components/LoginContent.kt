package com.juanma.blogmvvm.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juanma.blogmvvm.R
import com.juanma.blogmvvm.presentation.components.DefaultButton
import com.juanma.blogmvvm.presentation.components.DefaultTextField
import com.juanma.blogmvvm.presentation.ui.theme.BlogMVVMTheme
import com.juanma.blogmvvm.presentation.ui.theme.Darkgray500
import com.juanma.blogmvvm.presentation.ui.theme.Red500

@Composable
fun LoginContent(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        BoxHeader()
        CardForm()
    }
}

@Composable
fun BoxHeader(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(Red500)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(130.dp),
                painter = painterResource(id = R.drawable.control),
                contentDescription = "Control de Xbox 360"
            )
            Text(
                text = "Gamer App"
            )
        }
    }
}

@Composable
fun CardForm(){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 200.dp),
        backgroundColor = Darkgray500
    ){
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                modifier = Modifier.padding(
                    top = 40.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                ),
                text = "LOGIN",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Por favor inicia sesión para continuar",
                fontSize = 12.sp,
                color = Color.Gray
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 25.dp),
                value = email,
                onValueChange = { email = it},
                label = "Correo electronico",
                icon = Icons.Default.Email,
                keyboardType = KeyboardType.Email
            )
            DefaultTextField(
                modifier = Modifier.padding(top = 5.dp),
                value = password,
                onValueChange = { password = it},
                label = "Contraseña",
                icon = Icons.Default.Lock,
                hideText = true
            )
            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                text = "INICIAR SESIÓN",
                onClick = {  }
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BlogMVVMTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginContent()
        }
    }
}