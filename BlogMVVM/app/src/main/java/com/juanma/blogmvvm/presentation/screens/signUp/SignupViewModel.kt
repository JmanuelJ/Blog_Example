package com.juanma.blogmvvm.presentation.screens.signUp

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.use_cases.auth.AuthUseCases
import com.juanma.blogmvvm.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases): ViewModel() {
    //State
    var state by mutableStateOf(SignupState())
        private set
    //User name
    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
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
    //Confirm contraseña
    var iscomfirmPassword by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by  mutableStateOf("")
        private set
    //Button
    var isEnabledLoginButton = false

    var signupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var user = User()

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onSignup(){
        user.username= state.username
        user.email = state.email
        user.password = state.password
        signUp(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }

    fun signUp(user: User) = viewModelScope.launch {
        signupResponse = Response.Loading
        val result = authUseCases.signUp(user)
        signupResponse = result
    }

    fun enabledLoginButton(){
        isEnabledLoginButton = isEmailValid&&
                isPasswordValid &&
                isUsernameValid &&
                iscomfirmPassword
    }

    fun validateUsername(){
        if(state.username.length >= 5){
            isUsernameValid = true
            usernameErrMsg = ""
        }
        else{
            isUsernameValid = false
            usernameErrMsg = "Al menos 5 caracteres"
        }
        enabledLoginButton()
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
            passwordErrMsg  = ""
        }
        else{
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 carateres"
        }
        enabledLoginButton()
    }

    fun validateConfirmPassword(){
        if(state.password == state.confirmPassword){
            iscomfirmPassword= true
            confirmPasswordErrMsg= ""
        }
        else{
            iscomfirmPassword = false
            confirmPasswordErrMsg= "Las contraseñas no coinciden"
        }
        enabledLoginButton()
    }
}