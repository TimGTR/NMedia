package ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.adapter.PostInteractionListener
import ru.netology.nmedia.repository.Post
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl
import java.math.RoundingMode
import java.text.DecimalFormat

class PostViewModel : ViewModel(), PostInteractionListener {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()

    val currentPost = MutableLiveData<Post?> (null)
    override fun onLikeClicked(post: Post) = repository.likeById(post.id)
    override fun onShareClicked(post: Post) = repository.getShareCountById(post.id)
    override fun onRemoveClicked(post: Post) = repository.removeById(post.id)

    fun onSaveButtonClicked(content: String) {
        if (content.isBlank()) return
        val newPost = Post(
            id = PostRepository.NEW_POST_ID,
            author = "Me",
            content = content,
            published = "Today"
        )
        repository.save(newPost)
        currentPost.value = null
    }


}

