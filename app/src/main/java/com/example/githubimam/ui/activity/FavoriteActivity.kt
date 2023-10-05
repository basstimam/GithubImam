package com.example.githubimam.ui.activity

import FavoriteAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.githubuserapplication.view.ViewModelFactory
import com.example.githubimam.databinding.ActivityFavoriteBinding
import com.example.githubimam.ui.viewmodel.FavoriteViewmodel

class FavoriteActivity : AppCompatActivity() {

    private var _binding: ActivityFavoriteBinding? = null

    private val binding get() = _binding

    private val favoriteViewModel by lazy {
        val factory = ViewModelFactory.getInstance(application)
        ViewModelProvider(this, factory).get(FavoriteViewmodel::class.java)
    }
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        favoriteAdapter = FavoriteAdapter()


        favoriteViewModel.getAllFavorites().observe(this) { favorite ->
            if (favorite != null) {
                favoriteAdapter.setFavorites(favorite)
                Log.d("FavoriteActivity", "onCreate: $favorite")
            }

        }





        binding?.rvFavorite?.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = favoriteAdapter
            setHasFixedSize(
                true
            )
        }
    }
}