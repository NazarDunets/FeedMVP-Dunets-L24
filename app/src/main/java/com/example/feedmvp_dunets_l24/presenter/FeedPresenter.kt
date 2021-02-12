package com.example.feedmvp_dunets_l24.presenter

import com.example.feedmvp_dunets_l24.model.PostUiModel
import com.example.feedmvp_dunets_l24.repository.PostsRepository
import com.example.feedmvp_dunets_l24.shared.CancelableOperation
import com.example.feedmvp_dunets_l24.shared.Result

interface FeedView {
    fun showFeed(posts: List<PostUiModel>)
    fun showError(error: String)
}

class FeedPresenter(
    private val postsRepository: PostsRepository,
    private val postsUiMapper: PostsUiMapper
) {
    private var view: FeedView? = null
    private var cancelableOperation: CancelableOperation? = null

    fun attachView(feedView: FeedView) {
        view = feedView

        cancelableOperation = postsRepository.getPosts()
            .map(postsUiMapper::map)
            .postOnMainThread(::showResult)
    }

    fun detachView() {
        view = null
        cancelableOperation?.cancel()
    }

    private fun showResult(result: Result<List<PostUiModel>, String>) {
        if (result.isError) {
            view?.showError(result.errorResult)
        } else {
            view?.showFeed(result.successResult)
        }
    }
}