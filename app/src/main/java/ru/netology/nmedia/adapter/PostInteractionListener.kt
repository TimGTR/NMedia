package ru.netology.nmedia.adapter

import ru.netology.nmedia.repository.Post

interface PostInteractionListener {
    fun onLikeClicked(post: Post)
    fun onShareClicked(post: Post)
    fun onRemoveClicked(post: Post)
    fun onEditClicked(post: Post)

}