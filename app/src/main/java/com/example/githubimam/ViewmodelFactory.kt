package com.app.githubuserapplication.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubimam.data.database.FavoriteUserRepository
import com.example.githubimam.ui.viewmodel.FavoriteViewmodel

class ViewModelFactory private constructor(private val favUserRepos: FavoriteUserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(FavoriteUserRepository(application))
                }
            }
            return INSTANCE as ViewModelFactory
        }


    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewmodel::class.java)) {
            return FavoriteViewmodel(favUserRepos) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }


}