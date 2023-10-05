import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubimam.MainAdapter
import com.example.githubimam.data.database.entity.FavoriteUserEntity
import com.example.githubimam.databinding.ItemUserBinding
import com.example.githubimam.ui.activity.DetailUserActivity

class FavoriteAdapter :
    ListAdapter<FavoriteUserEntity, FavoriteAdapter.FavoriteUserViewHolder>(FavoriteUserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return FavoriteUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)



        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            val bundle = Bundle()
            bundle.putString(MainAdapter.NAME, current.login )
            intent.putExtras(bundle)
            holder.itemView.context.startActivity(intent)
        }



    }

    inner class FavoriteUserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoriteUser: FavoriteUserEntity) {
            binding.profileName.text = favoriteUser.login
            Glide.with(itemView.context)
                .load(favoriteUser.avatar_url)
                .into(binding.profileImg)
        }


    }

    fun setFavorites(favorites: List<FavoriteUserEntity>) {
        submitList(favorites)

    }
}

class FavoriteUserDiffCallback : DiffUtil.ItemCallback<FavoriteUserEntity>() {
    override fun areItemsTheSame(
        oldItem: FavoriteUserEntity,
        newItem: FavoriteUserEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FavoriteUserEntity,
        newItem: FavoriteUserEntity
    ): Boolean {
        return oldItem == newItem
    }
}
