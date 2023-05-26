package com.juanma.blogmvvm.domain.use_cases.posts

import com.juanma.blogmvvm.domain.model.Post
import com.juanma.blogmvvm.domain.repository.PostsRepository
import java.io.File
import javax.inject.Inject

class DeletePost @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(idPost: String) = repository.delete(idPost)
}