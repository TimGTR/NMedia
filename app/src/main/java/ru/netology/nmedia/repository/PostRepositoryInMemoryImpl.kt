package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = List(1000) {index ->
        Post(
            id = index + 1L,
            author = "Timur",
            content = "Контен поста №${index + 1}",
            published = "03/07/2022"
        )
    }


    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id != id) post
            else post.copy(likedByMe = !post.likedByMe)
        }
        posts = posts.map { post ->
            if (post.likedByMe) post.copy(likesCount = post.likesCount + 1)
            else post.copy(likesCount = post.likesCount - 1)

        }
        data.value = posts
    }


    override fun getShareCountById(id: Long) {
        posts = posts.map { post ->
            post.copy(shareCount = post.shareCount + 1)

        }
        data.value = posts
    }


}


