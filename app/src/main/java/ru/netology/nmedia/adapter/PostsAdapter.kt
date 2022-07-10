package ru.netology.nmedia.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostCardBinding
import ru.netology.nmedia.repository.Post
import java.math.RoundingMode
import java.text.DecimalFormat



class PostsAdapter(
    private val posts: List<Post>,
    private val onLikeClicked: (Post) -> Unit,
    private val onShareClicked: (Post) -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: PostCardBinding,
                     private val onLikeClicked: (Post) -> Unit,
                     private val onShareClicked: (Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) = with(binding){
            authorName.text = post.author
            date.text = post.published
            text.text = post.content
            likesCount.text = remakeCount(post.likesCount)
            shareCount.text = remakeCount(post.shareCount)
            visibleCount.text = remakeCount(post.visibleCount)
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_red_heart_24 else R.drawable.ic_heart_24)
            like.setOnClickListener {
                onLikeClicked(post)
            }
            share.setOnClickListener {
                onShareClicked(post)

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
        return  ViewHolder(binding, onLikeClicked, onShareClicked)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("PostsAdapter", "onBind: $position")
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = posts.size


}