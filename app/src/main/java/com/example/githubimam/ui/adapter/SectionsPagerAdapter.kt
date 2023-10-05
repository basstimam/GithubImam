package com.example.githubimam.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubimam.ui.fragment.FollowersFragment
import com.example.githubimam.ui.fragment.FollowingFragment


class SectionsPagerAdapter(activity: AppCompatActivity, private val login: Bundle) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()

        }

        fragment?.arguments = login
        return fragment as Fragment

    }

}