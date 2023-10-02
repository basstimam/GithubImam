package com.example.githubimam.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.githubimam.R
import com.example.githubimam.databinding.ActivityThemeBinding
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.switchmaterial.SwitchMaterial

class ThemeActivity : AppCompatActivity() {
    private var _binding: ActivityThemeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme: SwitchMaterial = findViewById(R.id.switch_theme)

        switchTheme.setOnCheckedChangeListener{
                _: CompoundButton?, isChecked: Boolean ->

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }



    }
}