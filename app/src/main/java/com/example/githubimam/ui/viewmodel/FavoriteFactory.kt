package com.example.githubimam.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubimam.data.database.FavoriteUserRepository

class FavoriteViewModelFactory(private val favoriteUserRepository: FavoriteUserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteViewmodel(favoriteUserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
