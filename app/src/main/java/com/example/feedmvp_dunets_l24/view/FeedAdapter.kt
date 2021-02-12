package com.example.feedmvp_dunets_l24.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feedmvp_dunets_l24.R
import com.example.feedmvp_dunets_l24.databinding.PostBannedItemBinding
import com.example.feedmvp_dunets_l24.databinding.PostNormalItemBinding
import com.example.feedmvp_dunets_l24.model.PostUiModel

class FeedAdapter :
    androidx.recyclerview.widget.ListAdapter<PostUiModel, RecyclerView.ViewHolder>(PostsDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isBanned) R.layout.post_banned_item
        else R.layout.post_normal_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.post_normal_item -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                PostNormalViewHolder(view)
            }
            R.layout.post_banned_item -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                BannedUserViewHolder(view)
            }
            else -> PostNormalViewHolder(View(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostNormalViewHolder -> holder.bind(getItem(position))
            is BannedUserViewHolder -> holder.bind(getItem(position))
        }
    }

}

class PostNormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = PostNormalItemBinding.bind(itemView)

    fun bind(post: PostUiModel) {
        binding.apply {
            tvTitle.text = post.title
            tvDescription.text = post.body
            tvWarning.visibility = if (post.hasWarning) View.VISIBLE else View.GONE
            root.setBackgroundColor(post.bgColorInt)
            tvUser.text = post.userId.toString()
        }
    }
}

class BannedUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = PostBannedItemBinding.bind(itemView)

    fun bind(post: PostUiModel) {
        binding.tvTitle.text = post.title
    }
}

class PostsDiffCallback : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem == newItem
    }

}

