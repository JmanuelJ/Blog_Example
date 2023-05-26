package com.juanma.blogmvvm.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.juanma.blogmvvm.domain.model.Post
import com.juanma.blogmvvm.presentation.navigation.DetailsScreen

@Composable
fun PostCard(navController: NavHostController, post: Post) {
    Card(
        modifier = Modifier.padding(top = 15.dp,
        bottom = 15.dp)
            .clickable {
                       navController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        contentColor = Color.White
    ) {
        Column() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .height(170.dp),
                model = post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .padding(
                        horizontal = 15.dp,
                        vertical = 10.dp
                    ),
                text = post.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(
                    horizontal = 15.dp,
                    vertical = 3.dp
                ),
                text = post.user?.username ?: "",
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp,
                    vertical = 3.dp
                ),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )
        }
    }
}