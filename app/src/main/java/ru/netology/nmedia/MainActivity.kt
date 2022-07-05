package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //var myTW = findViewById<TextView>(R.id.likesCount)

        val postInfo = PostInfo()

        val post = Post(
            id = 1,
            author = "Timur",
            content = "Это первый пост в НМедиа",
            published = "03/07/2022"
        )

        binding.render(post, postInfo)

        binding.like.setOnClickListener {
            post.likedByMe = !post.likedByMe
            binding.like.setImageResource(getLikeIconResId(post.likedByMe))
            getLikesCount(post.likedByMe,postInfo)
            //myTW.setText(postInfo.likesCount) //через findviewbyid
            binding.likesCount.text =
                remakeLikesCount(postInfo)
         }

        binding.share.setOnClickListener {
            getShareCount(postInfo)
            binding.shareCount.text =
                remakeShareCount(postInfo)

        }



    }
    private fun ActivityMainBinding.render(post: Post, postInfo: PostInfo) {
        authorName.text = post.author
        date.text = post.published
        text.text = post.content
        likesCount.text = postInfo.likesCount.toString()
        shareCount.text = postInfo.shareCount.toString()
        visibleCount.text = postInfo.visibleCount.toString()
        like.setImageResource(getLikeIconResId(post.likedByMe))
    }

    @DrawableRes
    private fun getLikeIconResId(liked: Boolean) =
        if(liked) {
            R.drawable.ic_red_heart_24
            //postInfo.likesCount++
        } else {
            R.drawable.ic_heart_24
            //postInfo.likesCount--
        }
    private fun getLikesCount(liked: Boolean, postInfo: PostInfo) =
        if(liked) {
            postInfo.likesCount++

        } else {
            postInfo.likesCount--
        }

    private fun getShareCount(postInfo: PostInfo) =
        postInfo.shareCount++

    private fun remakeShareCount(postInfo: PostInfo) =
        if (postInfo.shareCount < 1000) {
            postInfo.shareCount.toString()
        } else if (postInfo.shareCount < 1_000_000) {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            "${df.format((postInfo.shareCount/1000).toDouble())}K"
        } else {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            "${df.format((postInfo.shareCount/1000000).toDouble())}M"
        }

    private fun remakeLikesCount(postInfo: PostInfo) =
        if (postInfo.likesCount < 1000) {
            postInfo.likesCount.toString()
        } else if (postInfo.likesCount < 1_000_000) {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            "${df.format((postInfo.likesCount/1000).toDouble())}K"
        } else {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            "${df.format((postInfo.likesCount/1000000).toDouble())}M"
        }


}



