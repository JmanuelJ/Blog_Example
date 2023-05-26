package com.juanma.blogmvvm.presentation.screens.my_posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanma.blogmvvm.domain.model.Post
import com.juanma.blogmvvm.domain.model.Response
import com.juanma.blogmvvm.domain.use_cases.auth.AuthUseCases
import com.juanma.blogmvvm.domain.use_cases.posts.PostsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostsViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases,
    private val authUseCases: AuthUseCases
): ViewModel() {

    var postsResponse by mutableStateOf<Response<List<Post>>?> (null)
    var deleteResponse by mutableStateOf<Response<Boolean>?> (null)
    var currentUser = authUseCases.getCurrentUser()

    init {
        getPosts( )
    }

    fun delete(idPost: String) = viewModelScope.launch {
        deleteResponse = Response.Loading
        val result = postsUseCases.deletePost(idPost)
        deleteResponse = result
    }

    fun getPosts() = viewModelScope.launch {
        postsResponse = Response.Loading
        postsUseCases.getPostsByIdUser(currentUser?.uid ?: "").collect(){response ->
            postsResponse = response
        }
    }
}