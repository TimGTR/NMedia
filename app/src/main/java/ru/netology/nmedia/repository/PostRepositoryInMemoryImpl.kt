package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.math.RoundingMode
import java.text.DecimalFormat

class PostRepositoryInMemoryImpl: PostRepository {
    private var post = Post(
        id = 1,
        author = "Timur",
        content = "Это первый пост в НМедиа",
        published = "03/07/2022"
    )

    private val data = MutableLiveData (post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }

    override fun getLikesCount() {
        post = post.copy(likesCount =
        if (post.likedByMe) post.likesCount + 1 else post.likesCount - 1
       )
        data.value = post
    }

    override fun getShareCount() {
        post = post.copy(shareCount = post.shareCount + 1)
        data.value = post
    }

    fun remakeCount(count: Int): String {
        return if (count < 1000) {
            count.toString()
        } else if (count < 1_000_000) {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            "${df.format(count / 1000.0)}K"
        } else {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.CEILING
            "${df.format(count / 1000000.0)}M"
        }
    }

}


