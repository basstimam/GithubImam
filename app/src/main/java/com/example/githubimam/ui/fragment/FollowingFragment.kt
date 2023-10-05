package com.example.githubimam.ui.fragment

import UserViewmodel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubimam.databinding.FragmentFollowingBinding
import com.example.githubimam.ui.adapter.FollowingAdapter

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private val userViewmodel: UserViewmodel by activityViewModels()
    private val followingAdapter = FollowingAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)

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

            userViewmodel.getFollowing(login)
        }


    }

    private fun observeFollowing() {
        userViewmodel.following.observe(viewLifecycleOwner) { following ->
            if (following != null) {
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
