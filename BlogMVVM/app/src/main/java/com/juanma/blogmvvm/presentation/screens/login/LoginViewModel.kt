package com.juanma.blogmvvm.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    //Email
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState <Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState <String> = mutableStateOf("")
    //Password
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState <Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState <String> = mutableStateOf("")
    //Button
    var isEnabledLoginButton = false

    fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid.value && isPasswordValid.value
    }

    fun validateEmail(){
        //Valida si es Email
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailErrMsg.value = ""
        }
        else{
            isEmailValid.value = false
            emailErrMsg.value = "El email no es valido"
        }
        enabledLoginButton()
    }

    fun validatePassword(){
        if(password.value.length >= 6){
            isPasswordValid.value = true
            passwordErrMsg.value = ""
        }
        else{
            isPasswordValid.value = false
            passwordErrMsg.value = "Al menos 6 carateres"
        }
        enabledLoginButton()
    }
}