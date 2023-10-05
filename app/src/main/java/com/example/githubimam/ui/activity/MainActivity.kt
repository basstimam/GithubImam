package com.example.githubimam.ui.activity


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubimam.MainAdapter
import com.example.githubimam.R
import com.example.githubimam.data.response.ItemsItem
import com.example.githubimam.databinding.ActivityMainBinding
import com.example.githubimam.ui.settings.SettingPreferences
import com.example.githubimam.ui.settings.ThemeViewmodel
import com.example.githubimam.ui.settings.ViewModelFactory
import com.example.githubimam.ui.settings.dataStore
import com.example.githubimam.ui.viewmodel.MainViewModel



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val mainViewModel by viewModels<MainViewModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(true)
        supportActionBar?.hide()
        setupMain()




    }


    private fun setupMain() {
        mainViewModel.githubUser.observe(this) { user ->

            if (user != null) {




                showLoading(false)
                val count = user.size
                Toast.makeText(this, "Found $count user", Toast.LENGTH_SHORT).show()


            }




            setUserData(user)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        val changeThemeMenuItem = binding.searchBar.menu.findItem(R.id.menu_item_change_theme)
        val favoriteMenuItem = binding.searchBar.menu.findItem(R.id.menu_item_favorite)

        val preferences = SettingPreferences.getInstance(application.dataStore)
        val themeViewModel =
            ViewModelProvider(this, ViewModelFactory(preferences))[ThemeViewmodel::class.java]
        themeViewModel.getThemeSetting().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                changeThemeMenuItem.setIcon(R.drawable.baseline_invert_colors_24_white)
                favoriteMenuItem.setIcon(R.drawable.baseline_favorite_24_white)


            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }




        binding.searchBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_change_theme -> {
                    val isDarkModeActive = isDarkModeEnabled()


                    Log.d("MainActivity", "isDarkModeActive: $isDarkModeActive")
                    val newTheme = !isDarkModeActive
                    setTheme(newTheme)
                    themeViewModel.saveThemeSetting(newTheme)
                    true
                }

                R.id.menu_item_favorite -> {
                    Intent(this, FavoriteActivity::class.java).also {
                        startActivity(it)
                    }
                    true
                }

                else -> false
            }
        }


        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.text = searchView.text
                    val search = searchView.text
                    searchView.hide()

                    performSearch(search.toString())
                    false
                }


        }


    }


    private fun performSearch(query: String) {

        showLoading(true)

            mainViewModel.searchUser(query)






    }


    private fun setUserData(userData: List<ItemsItem>) {

        val adapter = MainAdapter()
        adapter.submitList(userData)
        binding.rvUser.adapter = adapter

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvUser.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvUser.visibility = View.VISIBLE
        }
    }

    private fun setTheme(isDark: Boolean) {


        if (isDark) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)




        }


    }


    private fun isDarkModeEnabled(): Boolean {
        return when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }

}