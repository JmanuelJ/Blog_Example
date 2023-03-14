package com.juanma.blogmvvm.presentation.screens.signUp

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(): ViewModel() {
    //User name
    var username: MutableState<String> = mutableStateOf("")
    var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usernameErrMsg: MutableState<String> = mutableStateOf("")
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
    //Confirm contraseña
    var confirmPassword: MutableState<String> = mutableStateOf("")
    var iscomfirmPassword: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid.value &&
                isPasswordValid.value &&
                isUsernameValid.value &&
                iscomfirmPassword.value
    }

    fun validateUsername(){
        if(username.value.length >= 5){
            isUsernameValid.value = true
            usernameErrMsg.value = ""
        }
        else{
            isUsernameValid.value = false
            usernameErrMsg.value = "Al menos 5 caracteres"
        }
        enabledLoginButton()
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

    fun validateConfirmPassword(){
        if(password.value  == confirmPassword.value){
            iscomfirmPassword.value = true
            confirmPasswordErrMsg.value = ""
        }
        else{
            iscomfirmPassword.value = false
            confirmPasswordErrMsg.value = "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }
}