package com.juanma.blogmvvm.presentation.screens.profile_update

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.model.User
import com.juanma.blogmvvm.domain.use_cases.users.UsersUseCases
import com.juanma.blogmvvm.presentation.utils.ComposeFileProvider
import com.juanma.blogmvvm.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val userUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
): ViewModel() {
    //State
    var state by mutableStateOf(ProfileUpdateState())
        private set

    var usernameErrMsg by mutableStateOf("")
        private set

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    //Response
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set


    val resultingActivityHandler = ResultingActivityHandler()

    //File
    var file: File? = null

    init{
        state = state.copy(
            username = user.username,
            image = user.image
        )
    }

    fun saveImage() = viewModelScope.launch {
        if(file != null){
            saveImageResponse = Response.Loading
            val result = userUseCases.saveImage(file!!)
            saveImageResponse = result
        }
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if(result != null){
            file = ComposeFileProvider.createFileFromUri(context, result)
             state = state.copy(image = result.toString())
        }
    }

   fun takePhoto() = viewModelScope.launch {
       val result = resultingActivityHandler.takePicturePreview()
       if ( result != null){
           state = state.copy(ComposeFileProvider.getPathFromBitmap(context,result))
           file = File(state.image)
       }
   }

    fun onUpdate(uri: String){
        val myUser = User(
            id = user.id,
            username= state.username,
            image = uri
        )
        update(myUser)
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = userUseCases.update(user)
        updateResponse = result

    }
    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }

    fun validateUsername(){
        if(state.username.length >= 5){
            usernameErrMsg = ""
        }
        else{
            usernameErrMsg = "Al menos 5 caracteres"
        }
    }
}