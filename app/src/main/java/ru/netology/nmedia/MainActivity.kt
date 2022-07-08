package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.repository.Post
import ru.netology.nmedia.viewmodel.PostViewModel
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //var myTW = findViewById<TextView>(R.id.likesCount)

        val viewModel:PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            binding.render(post)

        }


        binding.like.setOnClickListener {
            viewModel.like()
            viewModel.getLikesCount()


        }

       binding.share.setOnClickListener {
            viewModel.getShareCount()

       }


    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.author
        date.text = post.published
        text.text = post.content
        likesCount.text = post.likesCount.toString()
        shareCount.text = post.shareCount.toString()
        visibleCount.text = post.visibleCount.toString()
        like.setImageResource(
            if (post.likedByMe) R.drawable.ic_red_heart_24 else R.drawable.ic_heart_24)

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



