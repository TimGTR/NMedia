package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class PostRepositoryInMemoryImpl : PostRepository {

    private var nextId = GENERATED_POSTS_AMOUNT.toLong()
    private var posts = List(GENERATED_POSTS_AMOUNT) { index ->
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
            if (post.id == id) {
                if (post.likedByMe) post.copy(likesCount = post.likesCount + 1)
                else post.copy(likesCount = post.likesCount - 1)
            } else post


        }
        data.value = posts
    }


    override fun getShareCountById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(shareCount = post.shareCount + 1)

            } else post

        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter {post ->
            post.id != id
        }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)
    }

    private fun update(post: Post) {
        data.value = posts.map {
            if(it.id == post.id) post else it

        }
    }



    private fun insert(post: Post) {
        data.value = listOf(post.copy(
            id= ++nextId)) + posts
    }

    private companion object {
        const val GENERATED_POSTS_AMOUNT = 1000
    }
}


