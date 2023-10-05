import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubimam.data.database.entity.FavoriteUserEntity
import com.example.githubimam.databinding.ItemUserBinding

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
