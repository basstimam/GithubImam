package com.example.githubimam.detail_user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubimam.data.response.FollowingResponseItem
import com.example.githubimam.databinding.ItemUserBinding

class FollowingAdapter :    ListAdapter<FollowingResponseItem, FollowingAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val following = getItem(position)
        holder.bind(following)
    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(following: FollowingResponseItem) {
            binding.profileName.text = following.login

            Glide.with(itemView.context)
                .load(following.avatarUrl)
                .circleCrop()
                .into(binding.profileImg)

        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowingResponseItem>() {
            override fun areItemsTheSame(
                oldItem: FollowingResponseItem,
                newItem: FollowingResponseItem
            ): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(
                oldItem: FollowingResponseItem,
                newItem: FollowingResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}