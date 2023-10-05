package com.example.githubimam.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubimam.R
import com.example.githubimam.data.database.entity.FavoriteUserEntity
import com.example.githubimam.databinding.ActivityDetailUserBinding
import com.example.githubimam.ui.adapter.SectionsPagerAdapter
import com.example.githubimam.ui.viewmodel.DetailUserFactory
import com.example.githubimam.ui.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val detailUserViewModel by viewModels<DetailUserViewModel> {
        DetailUserFactory(application)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(true)

        setupDetailUser()


    }

    private fun setupDetailUser() {
        val bundle = intent.extras
        val login = Bundle()
        login.putString("NAME", bundle?.getString("NAME"))











        if (bundle != null) {
            val username = bundle.getString("NAME")

            if (username != null) {


                detailUserViewModel.detailUser.observe(this) { detailUser ->
                    if (detailUser != null) {


                        val sectionsPagerAdapter = SectionsPagerAdapter(this, login)
                        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
                        viewPager.adapter = sectionsPagerAdapter
                        val tabs: TabLayout = findViewById(R.id.tabs)
                        TabLayoutMediator(tabs, viewPager) { tab, position ->
                            tab.text = resources.getString(TAB_TITLES[position])
                        }.attach()
                        supportActionBar?.elevation = 0f

                        showLoading(false)
                    }


                    with(binding) {
                        detailProfileName.text = detailUser.name
                        detailFollowers.text =
                            resources.getString(R.string.followers, detailUser.followers)
                        detailFollowing.text =
                            resources.getString(R.string.following, detailUser.following)
                        detailUsername.text = detailUser.login

                    }
                    Glide.with(this@DetailUserActivity)
                        .load(detailUser.avatarUrl)
                        .into(binding.detailProfileImg)
                }


                detailUserViewModel.getDetailUser(username)

                detailUserViewModel.getUserByLogin(username).observe(this) {
                    if (it != null) {
                        binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_24)
                        binding.fabFavorite.setOnClickListener {

                            detailUserViewModel.delete(username)

                        }


                    } else {
                        binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
                        binding.fabFavorite.setOnClickListener {
                            val favoriteUserEntity = FavoriteUserEntity(
                                0,
                                detailUserViewModel.detailUser.value?.login,
                                detailUserViewModel.detailUser.value?.avatarUrl,
                            )

                            detailUserViewModel.insert(favoriteUserEntity)
                        }

                    }
                }


            }


        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            with(binding) {
                progressBar.visibility = View.VISIBLE
                wrapHeading.visibility = View.GONE
                binding.tabs.visibility = View.GONE
            }
        } else {
            with(binding) {
                progressBar.visibility = View.GONE
                wrapHeading.visibility = View.VISIBLE
                binding.tabs.visibility = View.VISIBLE
            }

        }
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

}