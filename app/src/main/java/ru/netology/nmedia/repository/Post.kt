package ru.netology.nmedia.repository

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val likesCount: Int = 999,
    val shareCount: Int = 995,
    val visibleCount: Int = 1000
    )