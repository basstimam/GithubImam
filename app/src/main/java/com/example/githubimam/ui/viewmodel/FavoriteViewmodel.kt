package com.example.githubimam.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubimam.data.database.FavoriteUserRepository
import com.example.githubimam.data.database.entity.FavoriteUserEntity

class FavoriteViewmodel(private val favoriteUserRepository: FavoriteUserRepository) : ViewModel() {

    fun getAllFavorites(): LiveData<List<FavoriteUserEntity>> =
        favoriteUserRepository.getAllFavorites()


}
