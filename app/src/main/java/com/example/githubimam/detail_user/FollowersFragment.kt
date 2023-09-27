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
import com.example.githubimam.detail_user.DetailUserActivity
import kotlin.math.log

class FollowersFragment : Fragment() {
    private lateinit var binding: FragmentFollowersBinding
    private val followersViewModel: FollowersViewModel by activityViewModels()
    private val followersAdapter = FollowersAdapter()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        setupRecyclerView()
        observeFollowers()















    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollowers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvFollowers.addItemDecoration(itemDecoration)
        binding.rvFollowers.adapter = followersAdapter

        val login = arguments?.getString("NAME")
        if (login != null) {

            followersViewModel.getFollowers(login)
        }


    }

    private fun observeFollowers() {
        followersViewModel.followers.observe(viewLifecycleOwner) { followers ->

            if (followers != null) {
                showLoading(false)
            }
            followersAdapter.submitList(followers)
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
