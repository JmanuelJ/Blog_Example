package com.juanma.blogmvvm.presentation.screens.my_posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.juanma.blogmvvm.domain.model.Post

@Composable
fun MyPostsContent(
    navController: NavHostController,
    posts: List<Post>
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 10.dp,
                top = 20.dp,
                bottom = 70.dp
            )
    ){
        items(
            items = posts
        ){post ->
            MyPostCard(navController = navController, post = post)
        }
    }
}