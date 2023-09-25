package com.example.githubimam


import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubimam.data.response.ItemsItem
import com.example.githubimam.databinding.ItemUserBinding



class MainAdapter: ListAdapter<ItemsItem, MainAdapter.ViewHolder> (DIFF_CALLBACK){





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val users = getItem(position)
        holder.bind(users)
        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context,"Tombol telah ${users.login} diklik",Toast.LENGTH_SHORT).show()
        }





    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(users: ItemsItem){



            binding.profileName.text = users.login
            Glide.with(itemView.context)
                .load(users.avatarUrl)
                .into(binding.profileImg)












        }
    }

    companion object{
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<ItemsItem>(){
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

        }
    }


}
