package com.juanma.blogmvvm.presentation.screens.profile_update.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.juanma.blogmvvm.R
import com.juanma.blogmvvm.presentation.components.DefaultButton
import com.juanma.blogmvvm.presentation.components.DefaultTextField
import com.juanma.blogmvvm.presentation.components.DialogCapturePicture
import com.juanma.blogmvvm.presentation.screens.profile_update.ProfileUpdateViewModel
import com.juanma.blogmvvm.presentation.ui.theme.Darkgray500
import com.juanma.blogmvvm.presentation.ui.theme.Red500

@Composable
fun ProfileUpdateContent(navController: NavHostController, viewModel: ProfileUpdateViewModel = hiltViewModel()){

    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()
    var dialogState = remember{ mutableStateOf(false)}
    DialogCapturePicture(
        status = dialogState ,
        takePhoto = { viewModel.takePhoto()  },
        pickImage = { viewModel.pickImage()}
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Red500)
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                if( viewModel.state.image != ""){
                    AsyncImage(
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clip(CircleShape)
                            .clickable {
                                       dialogState.value = true
                            },
                        model = viewModel.state.image ,
                        contentDescription = "Selected image",
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .height(120.dp)
                            .clickable {
                                dialogState.value = true
                            },
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "User image"
                    )

                }
            }
        }
        Card(
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 170.dp),
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
                    text = "Actualizacion",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor ingresa estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it)},
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.usernameErrMsg,
                    validateField = {viewModel.validateUsername()}
                )
                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 40.dp),
                    text = "Actualizar Datos",
                    onClick = { viewModel.saveImage() },
                )
            }
        }
    }
}