package com.example.githubimam


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubimam.data.response.ItemsItem
import com.example.githubimam.databinding.ItemUserBinding
import com.example.githubimam.ui.activity.DetailUserActivity


class MainAdapter : ListAdapter<ItemsItem, MainAdapter.ViewHolder>(DIFF_CALLBACK) {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = getItem(position)

        holder.bind(users)

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            val bundle = Bundle()
            bundle.putString(NAME, users.login)
            bundle.putString(AVATAR, users.avatarUrl)
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)


        }


    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(users: ItemsItem) {
            binding.profileName.text = users.login
            Glide.with(itemView.context)
                .load(users.avatarUrl)
                .into(binding.profileImg)

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

        }

        const val NAME = "NAME"
        const val AVATAR = "AVATAR"
    }



}
