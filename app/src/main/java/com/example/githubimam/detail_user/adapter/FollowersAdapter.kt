package com.example.githubimam.detail_user.adapter




import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubimam.data.response.DetailUserResponse
import com.example.githubimam.data.response.ItemsItem
import com.example.githubimam.databinding.ItemUserBinding
import com.example.githubimam.detail_user.DetailUserActivity


class FollowersAdapter: ListAdapter<DetailUserResponse, FollowersAdapter.ViewHolder> (DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersAdapter.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: FollowersAdapter.ViewHolder, position: Int) {
        val users = getItem(position)
        holder.bind(users)


    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(users: DetailUserResponse){

            binding.profileName.text = users.login
            Glide.with(itemView.context)
                .load(users.avatarUrl)
                .into(binding.profileImg)












        }
    }

    companion object{
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<DetailUserResponse>(){
            override fun areItemsTheSame(oldItem: DetailUserResponse, newItem: DetailUserResponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DetailUserResponse, newItem: DetailUserResponse): Boolean {
                return oldItem == newItem
            }

        }

        const val NAME = "NAME"
        const val AVATAR = "AVATAR"
    }


}
