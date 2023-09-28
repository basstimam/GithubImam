import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubimam.data.response.FollowersResponseItem
import com.example.githubimam.databinding.ItemUserBinding

class FollowersAdapter : ListAdapter<FollowersResponseItem, FollowersAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val follower = getItem(position)
        holder.bind(follower)
    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(follower: FollowersResponseItem) {
            binding.profileName.text = follower.login
            Glide.with(itemView.context)
                .load(follower.avatarUrl)
                .into(binding.profileImg)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FollowersResponseItem>() {
            override fun areItemsTheSame(oldItem: FollowersResponseItem, newItem: FollowersResponseItem): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(oldItem: FollowersResponseItem, newItem: FollowersResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
