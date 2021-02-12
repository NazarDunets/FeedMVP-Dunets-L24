package com.example.feedmvp_dunets_l24.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feedmvp_dunets_l24.databinding.FeedFragmentBinding
import com.example.feedmvp_dunets_l24.model.PostUiModel
import com.example.feedmvp_dunets_l24.presenter.FeedPresenter
import com.example.feedmvp_dunets_l24.presenter.FeedView

class FeedFragment : Fragment(), FeedView {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private var _binding: FeedFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    private lateinit var feedAdapter: FeedAdapter

    private lateinit var presenter: FeedPresenter

    override fun showFeed(posts: List<PostUiModel>) {
        binding.pbFeedLoading.visibility = View.GONE
        binding.rvPosts.visibility = View.VISIBLE
        feedAdapter.submitList(posts)
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = FeedComponent.createPresenter(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        presenter.attachView(this)
    }

    private fun setupRv() {
        feedAdapter = FeedAdapter()

        binding.rvPosts.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = feedAdapter
            val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
        _binding = null
    }
}