package com.example.feedmvp_dunets_l24.api

import com.example.feedmvp_dunets_l24.model.PostData
import retrofit2.Call
import retrofit2.http.GET

interface PostsService {
    @GET("/posts")
    fun getPosts(): Call<List<PostData>>
}