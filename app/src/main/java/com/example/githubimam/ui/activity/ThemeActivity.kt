package com.example.githubimam.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.githubimam.R
import com.example.githubimam.databinding.ActivityThemeBinding
import com.example.githubimam.ui.viewmodel.ThemeViewmodel
import com.example.githubimam.utils.SettingsPreferences
import com.example.githubimam.utils.dataStore
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.switchmaterial.SwitchMaterial
import androidx.lifecycle.ViewModel
import com.example.githubimam.ui.viewmodel.ThemeViewmodelFactory

class ThemeActivity : AppCompatActivity() {
    private var _binding: ActivityThemeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme: SwitchMaterial = findViewById(R.id.switch_theme)
        val pref = SettingsPreferences.getInstance(application.dataStore)
        val themeViewmodel = ViewModelProvider(this,ThemeViewmodelFactory(pref) ).get(ThemeViewmodel::class.java)


        themeViewmodel.getThemeSetting().observe(this){
           isDarkModeActive: Boolean ->
              if (isDarkModeActive){
                switchTheme.isChecked = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
              }
       }

        switchTheme.setOnCheckedChangeListener{
            _: CompoundButton?, isChecked: Boolean ->
            themeViewmodel.saveThemeSetting(isChecked)
        }



    }
}