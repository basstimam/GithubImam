package com.example.githubimam.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubimam.utils.SettingsPreferences
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope


class ThemeViewmodel(private val pref: SettingsPreferences):ViewModel(){
    fun getThemeSetting(): LiveData<Boolean>{
        return pref.getThemeSetting().asLiveData()
    }
    fun saveThemeSetting(isDarkMode: Boolean){
      viewModelScope.launch{
            pref.saveThemeSetting(isDarkMode)
      }
   }

}