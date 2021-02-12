package com.example.feedmvp_dunets_l24.view

import android.content.Context
import com.example.feedmvp_dunets_l24.api.PostsService
import com.example.feedmvp_dunets_l24.presenter.FeedPresenter
import com.example.feedmvp_dunets_l24.presenter.PostsUiMapper
import com.example.feedmvp_dunets_l24.repository.PostsMapper
import com.example.feedmvp_dunets_l24.repository.PostsRepository
import com.example.feedmvp_dunets_l24.repository.UsersRepository
import com.example.feedmvp_dunets_l24.shared.Multithreading
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FeedComponent {
    fun createPresenter(context: Context): FeedPresenter {
        return FeedPresenter(
            postsRepository = PostsRepository(
                multithreading = Multithreading(context),
                postsService = createService(),
                postsMapper = PostsMapper(UsersRepository())
            ),
            postsUiMapper = PostsUiMapper(context)
        )
    }

    private fun createService(): PostsService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsService::class.java)
    }
}