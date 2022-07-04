package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myTW = findViewById<TextView>(R.id.likes_count)

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
            //binding.likesCount.setText(postInfo.likesCount) //через binding
        }

//        binding.share.setOnClickListener {
//            getShareCount(postInfo)
//        }



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
        ++postInfo.shareCount

}



