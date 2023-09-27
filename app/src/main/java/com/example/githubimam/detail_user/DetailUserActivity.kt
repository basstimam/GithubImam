package com.example.githubimam.detail_user

import FollowersFragment
import android.R.attr.data
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubimam.R
import com.example.githubimam.databinding.ActivityDetailUserBinding
import com.example.githubimam.detail_user.adapter.SectionsPagerAdapter
import com.example.githubimam.detail_user.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private val detailUserViewModel by viewModels<DetailUserViewModel>()

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(true)

        setupDetailUser()



    }

    private fun setupDetailUser()
    {
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


                    binding.detailProfileName.text = detailUser.name
                    binding.detailFollowers.text = detailUser.followers.toString() + " Followers"
                    binding.detailFollowing.text = detailUser.following.toString() + " Following"
                    binding.detailUsername.text = detailUser.login

                    Glide.with(this@DetailUserActivity)
                        .load(detailUser.avatarUrl)
                        .into(binding.detailProfileImg)
                }


                detailUserViewModel.getDetailUser(username)














            }


        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.wrapHeading.visibility = View.GONE
            binding.tabs.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.wrapHeading.visibility = View.VISIBLE
            binding.tabs.visibility = View.VISIBLE

        }
    }



}