package ru.netology.nmedia

class Post (
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likedByMe: Boolean = false,
    var likesCount: Int = 999,
    var shareCount: Int = 1097,
    var visibleCount: Int = 999
    )