package com.example.githubimam.ui.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubimam.MainAdapter
import com.example.githubimam.R
import com.example.githubimam.ui.viewmodel.MainViewModel

import com.example.githubimam.data.response.ItemsItem

import com.example.githubimam.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val mainViewModel by viewModels<MainViewModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        showLoading(true)
        supportActionBar?.hide()
        setupMain()

binding.searchBar.setOnMenuItemClickListener{
    menuItem ->
    when(menuItem.itemId){
        R.id.menu_item_settings -> {
            val intent = Intent(this, ThemeActivity::class.java)
            startActivity(intent)
            true
        }
        else -> false
    }
}






    }






    private fun setupMain(){
        mainViewModel.githubUser.observe(this, { user ->
            if (user != null) {

                showLoading(false)
            }

            setUserData(user)
        })
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)


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
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}