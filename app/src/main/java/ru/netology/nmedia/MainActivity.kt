package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Timur",
            content = "Это первый пост в НМедиа",
            published = "03/07/2022"
        )

        binding.like.setOnClickListener {
            binding.like.setImageResource(R.drawable.ic_red_heart_24)
        }
        with(binding) {
            authorName.text = post.author
            date.text = post.published
            text.text = post.content
            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_red_heart_24)
            }
            like.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if(post.likedByMe) R.drawable.ic_red_heart_24 else R.drawable.ic_heart_24
                )
            }
        }

    }
}
