package com.example.githubimam.ui.fragment

import FollowersAdapter
import UserViewmodel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubimam.databinding.FragmentFollowersBinding

class FollowersFragment : Fragment() {
    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val userViewmodel: UserViewmodel by activityViewModels()
    private val followersAdapter = FollowersAdapter()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentFollowersBinding.inflate(inflater, container, false)

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

            userViewmodel.getFollowers(login)
        }


    }

    private fun observeFollowers() {
        userViewmodel.followers.observe(viewLifecycleOwner) { followers ->

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
