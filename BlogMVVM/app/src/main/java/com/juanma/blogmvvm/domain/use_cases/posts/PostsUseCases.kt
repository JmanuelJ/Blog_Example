package com.juanma.blogmvvm.domain.use_cases.posts

data class PostsUseCases(
    val create: CreatePost,
    val getPosts: GetPosts,
    val getPostsByIdUser: GetPostsByIdUser,
    val deletePost: DeletePost,
    val updatePost: UpdatePost
)
