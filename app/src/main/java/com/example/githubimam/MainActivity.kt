package com.example.githubimam


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubimam.data.MainViewModel

import com.example.githubimam.data.response.ItemsItem

import com.example.githubimam.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mainViewModel.githubUser.observe(this, { user ->
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
        mainViewModel.searchUser(query)
    }



    private fun setUserData(userData: List<ItemsItem>) {
        val adapter = MainAdapter()
        adapter.submitList(userData)
        binding.rvUser.adapter = adapter

    }


}