package com.example.feedmvp_dunets_l24.model

data class PostModel(
    val userId: Int,
    val title: String,
    val body: String,
    val userStatus: UserStatus
)