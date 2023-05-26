package com.juanma.blogmvvm.presentation.screens.new_post

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanma.blogmvvm.R
import com.juanma.blogmvvm.domain.model.Post
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.use_cases.auth.AuthUseCases
import com.juanma.blogmvvm.domain.use_cases.posts.PostsUseCases
import com.juanma.blogmvvm.presentation.utils.ComposeFileProvider
import com.juanma.blogmvvm.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postsUseCases: PostsUseCases,
    private val authUseCases: AuthUseCases
): ViewModel() {
    var state by mutableStateOf(NewPostState())
    //File
    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()
    //Response
    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //User sesion
    val currenUser = authUseCases.getCurrentUser()
    val radioOptions = listOf(
        CategoryRadioButtom("PC", R.drawable.icon_pc),
        CategoryRadioButtom("PS4", R.drawable.icon_ps4),
        CategoryRadioButtom("XBOX", R.drawable.icon_xbox),
        CategoryRadioButtom("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButtom("MOVIL", R.drawable.icon_movil),
    )

    fun createPost(post: Post) = viewModelScope.launch {
        createPostResponse = Response.Loading
        val result = postsUseCases.create(post, file!!)
        createPostResponse = result
    }

    fun onNewPost(){
        val post = Post(
                name = state.name,
            description = state.description,
            category = state.category,
            idUser = currenUser?.uid ?: ""
                )
        createPost(post)
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

    fun clearForm(){
        state = state.copy(
            name = "",
            category = "",
            description = "",
            image = ""
        )
        createPostResponse = null
    }

    fun onNameInput(name: String){
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String){
        state = state.copy(category = category)
    }

    fun onDescriptionInput(description: String){
        state = state.copy(description = description)
    }

    fun onImageInput(image: String){
        state = state.copy(image = image)
    }

}

data class CategoryRadioButtom(
    var category: String,
    var image: Int
)