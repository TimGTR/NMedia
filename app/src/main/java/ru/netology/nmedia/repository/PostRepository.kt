package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun getShareCountById(id: Long)
    fun removeById(id: Long)


}