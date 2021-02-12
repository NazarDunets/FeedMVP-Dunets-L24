package com.example.feedmvp_dunets_l24.presenter

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.feedmvp_dunets_l24.R
import com.example.feedmvp_dunets_l24.model.PostModel
import com.example.feedmvp_dunets_l24.model.PostUiModel
import com.example.feedmvp_dunets_l24.model.UserStatus
import com.example.feedmvp_dunets_l24.repository.PostsErrors
import com.example.feedmvp_dunets_l24.shared.Result

// It seems like an overkill for current app

class PostsUiMapper(private val context: Context) {
    fun map(postsResult: Result<List<PostModel>, PostsErrors>): Result<List<PostUiModel>, String> {
        return postsResult.mapSuccess { posts ->
            posts.map {
                val hasWarning = it.userStatus == UserStatus.WARNING
                val isBanned = it.userStatus == UserStatus.BANNED

                val bgColor = if (hasWarning) {
                    ContextCompat.getColor(context, R.color.warning_post_bg)
                } else {
                    ContextCompat.getColor(context, R.color.normal_post_bg)
                }

                val title =
                    if (isBanned) context.getString(R.string.user_banned, it.userId.toString())
                    else it.title

                PostUiModel(
                    userId = it.userId,
                    title = title,
                    body = it.body,
                    hasWarning = hasWarning,
                    isBanned = isBanned,
                    bgColorInt = bgColor
                )
            }
        }.mapError { postsErrors ->
            val errorStringRes = when (postsErrors) {
                PostsErrors.POSTS_NOT_LOADED -> R.string.error_posts_not_loaded
                PostsErrors.MOCK_ERROR -> R.string.error_mock
            }

            context.getString(errorStringRes)
        }
    }
}