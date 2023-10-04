package com.example.githubimam.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubimam.utils.SettingsPreferences

class ThemeViewmodelFactory(private val pref: SettingsPreferences): ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewmodel::class.java)) {
            return ThemeViewmodel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)

    }


}