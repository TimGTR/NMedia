package ru.netology.nmedia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.repository.Post
import java.math.RoundingMode
import java.text.DecimalFormat

typealias OnListener = (Post) -> Unit

class PostsAdapter(
    private val onLikeClicked: OnListener,
    private val onShareClicked: OnListener
) : ListAdapter<Post, PostsAdapter.ViewHolder>(DiffCallback) {


    private object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem

    }

    class ViewHolder(
        private val binding: PostCardBinding,
        private val onLikeClicked: OnListener,
        private val onShareClicked: OnListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: Post

        init {
            binding.like.setOnClickListener {
                onLikeClicked(post)
            }
            binding.share.setOnClickListener {
                onShareClicked(post)
            }
        }

        fun bind(post: Post) {
            this.post = post
            with(binding) {
                authorName.text = post.author
                date.text = post.published
                text.text = post.content
                likesCount.text = remakeCount(post.likesCount)
                shareCount.text = remakeCount(post.shareCount)
                visibleCount.text = remakeCount(post.visibleCount)
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_red_heart_24 else R.drawable.ic_heart_24
                )

            }
        }

        private fun remakeCount(count: Int) =
            if (count < 1000) {
                count.toString()
            } else if (count < 1_000_000) {
                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.CEILING
                "${df.format((count / 1000.0))}K"
            } else {
                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.CEILING
                "${df.format((count / 1000000.0))}M"
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("PostsAdapter", "onCreate:")
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onLikeClicked, onShareClicked)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("PostsAdapter", "onBind: $position")
        val post = getItem(position)
        holder.bind(post)
    }


}