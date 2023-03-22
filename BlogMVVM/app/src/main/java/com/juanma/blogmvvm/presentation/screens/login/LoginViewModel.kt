package com.juanma.blogmvvm.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    //State Form
    var state by mutableStateOf(LoginState())
            private set

    //Email
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set
    //Password
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set
    //Enable Button
    var isEnabledLoginButton = false
    //Login State
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    val currentUser = authUseCases.getCurrentUser()

    init{
        if(currentUser != null){//Sesion iniciada
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email )
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password )
    }

    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email,state.password)
        loginResponse = result
    }

    fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid && isPasswordValid
    }

    fun validateEmail(){
        //Valida si es Email
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrMsg = ""
        }
        else{
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }
        enabledLoginButton()
    }

    fun validatePassword(){
        if(state.password.length >= 6){
            isPasswordValid = true
            passwordErrMsg = ""
        }
        else{
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 carateres"
        }
        enabledLoginButton()
    }
}