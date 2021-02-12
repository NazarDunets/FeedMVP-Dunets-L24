package com.example.feedmvp_dunets_l24.repository

import com.example.feedmvp_dunets_l24.api.PostsService
import com.example.feedmvp_dunets_l24.model.PostData
import com.example.feedmvp_dunets_l24.model.PostModel
import com.example.feedmvp_dunets_l24.shared.AsyncOperation
import com.example.feedmvp_dunets_l24.shared.Multithreading
import com.example.feedmvp_dunets_l24.shared.Result

class PostsRepository(
    private val multithreading: Multithreading,
    private val postsService: PostsService,
    private val postsMapper: PostsMapper
) {

    fun getPosts(): AsyncOperation<Result<List<PostModel>, PostsErrors>> {
        val asyncOperation = multithreading.async<Result<List<PostData>, PostsErrors>> {
            val posts = postsService.getPosts().execute().body()
                ?: return@async Result.error(PostsErrors.POSTS_NOT_LOADED)

            return@async Result.success(posts)
        }
        return asyncOperation.map(postsMapper::map)
    }
}