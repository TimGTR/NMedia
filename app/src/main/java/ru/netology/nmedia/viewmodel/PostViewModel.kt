package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl
import java.math.RoundingMode
import java.text.DecimalFormat

class PostViewModel: ViewModel() {
    private val repository:PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun getLikesCount() = repository.getLikesCount()
    fun getShareCount() = repository.getShareCount()
    fun remakeLikesCount() {

    }

}