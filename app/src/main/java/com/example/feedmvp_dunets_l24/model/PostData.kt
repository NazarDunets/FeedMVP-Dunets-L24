package com.example.feedmvp_dunets_l24.model


import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)