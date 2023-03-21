package com.juanma.blogmvvm.presentation.screens.signUp

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.repository.AuthRepository
import com.juanma.blogmvvm.domain.use_cases.auth.AuthUseCases
import com.juanma.blogmvvm.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases): ViewModel() {
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

    private val _signUpFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Response<FirebaseUser>?> = _signUpFlow

    var user = User()

    fun onSignup(){
        user.username= username.value
        user.email = email.value
        user.password = password.value
        signUp(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }

    fun signUp(user: User) = viewModelScope.launch {
        _signUpFlow.value = Response.Loading
        val result = authUseCases.signUp(user)
        _signUpFlow.value = result
    }

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