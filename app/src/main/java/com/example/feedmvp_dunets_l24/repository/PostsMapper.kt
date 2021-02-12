package com.example.feedmvp_dunets_l24.repository

import com.example.feedmvp_dunets_l24.model.PostData
import com.example.feedmvp_dunets_l24.model.PostModel
import com.example.feedmvp_dunets_l24.model.UserStatus
import com.example.feedmvp_dunets_l24.shared.Result

class PostsMapper(
    private val usersRepository: UsersRepository
) {
    fun map(postsResult: Result<List<PostData>, PostsErrors>): Result<List<PostModel>, PostsErrors> {
        val bannedUsers = usersRepository.getUsersWithBan()
        val userWithWarning = usersRepository.getUsersWithWarning()

        return postsResult.mapSuccess { posts ->
            posts.map {
                when (it.userId) {
                    in bannedUsers -> PostModel(it.userId, "", "", UserStatus.BANNED)
                    in userWithWarning -> PostModel(
                        it.userId,
                        it.title,
                        it.body,
                        UserStatus.WARNING
                    )
                    else -> PostModel(it.userId, it.title, it.body, UserStatus.NORMAL)
                }
            }

        }
    }
}