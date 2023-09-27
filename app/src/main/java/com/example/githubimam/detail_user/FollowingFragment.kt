import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubimam.databinding.FragmentFollowersBinding
import com.example.githubimam.databinding.FragmentFollowingBinding
import com.example.githubimam.detail_user.DetailUserActivity
import com.example.githubimam.detail_user.adapter.FollowingAdapter
import com.example.githubimam.detail_user.viewmodel.FollowingViewmodel
import kotlin.math.log

class FollowingFragment : Fragment() {
    private lateinit var binding: FragmentFollowingBinding
    private val followingViewmodel: FollowingViewmodel by activityViewModels()
    private val followingAdapter = FollowingAdapter()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        setupRecyclerView()
        observeFollowing()















    }

    private fun setupRecyclerView() {

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollowing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)
        binding.rvFollowing.adapter = followingAdapter

        val login = arguments?.getString("NAME")
        if (login != null) {

            followingViewmodel.getFollowing(login)
        }


    }

    private fun observeFollowing() {
        followingViewmodel.following.observe(viewLifecycleOwner) { following ->
            if (following != null)
            {
                showLoading(false)
            }
            followingAdapter.submitList(following)
        }
    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}
