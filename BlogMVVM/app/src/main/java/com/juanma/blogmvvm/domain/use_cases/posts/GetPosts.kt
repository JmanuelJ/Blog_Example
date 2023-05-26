package com.juanma.blogmvvm.domain.use_cases.posts

import com.juanma.blogmvvm.domain.repository.PostsRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val repository:PostsRepository) {
    operator fun invoke() = repository.getPosts()
}