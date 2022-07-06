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


        val post = Post(
            id = 1,
            author = "Timur",
            content = "Это первый пост в НМедиа",
            published = "03/07/2022"
        )

        binding.render(post)

        binding.like.setOnClickListener {
            post.likedByMe = !post.likedByMe
            binding.like.setImageResource(getLikeIconResId(post))
            getLikesCount(post)
            //myTW.setText(postInfo.likesCount) //через findviewbyid
            binding.likesCount.text =
                remakeCount(post.likesCount)
        }

        binding.share.setOnClickListener {
            getShareCount(post)
            binding.shareCount.text =
                remakeCount(post.shareCount)

        }


    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.author
        date.text = post.published
        text.text = post.content
        likesCount.text = post.likesCount.toString()
        shareCount.text = post.shareCount.toString()
        visibleCount.text = post.visibleCount.toString()
        like.setImageResource(getLikeIconResId(post))
    }

    @DrawableRes
    private fun getLikeIconResId(post: Post) =
        if (post.likedByMe) {
            R.drawable.ic_red_heart_24
            //postInfo.likesCount++
        } else {
            R.drawable.ic_heart_24
            //postInfo.likesCount--
        }

    private fun getLikesCount(post: Post) =
        if (post.likedByMe) {
            post.likesCount++

        } else {
            post.likesCount--
        }

    private fun getShareCount(post: Post) =
        post.shareCount++

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



