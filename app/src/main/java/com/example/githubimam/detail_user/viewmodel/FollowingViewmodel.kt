package com.example.githubimam.detail_user.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubimam.data.response.FollowingResponseItem
import com.example.githubimam.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewmodel : ViewModel() {

    private val _following = MutableLiveData<List<FollowingResponseItem>>()
    val following: LiveData<List<FollowingResponseItem>> = _following

    companion object {
        const val TAG = "FollowingViewModel"
    }

    internal fun getFollowing(username: String) {

        val client = ApiConfig.getApiService().getFollowing(username)

        client.enqueue(object : Callback<List<FollowingResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowingResponseItem>>,
                response: Response<List<FollowingResponseItem>>
            ) {
                if (response.isSuccessful) {
                    _following.value = response.body()
                    Log.d(TAG, "Response: ${response.body()}}")

                } else {
                    Log.d(TAG, "Response not successful")
                }
            }

            override fun onFailure(call: Call<List<FollowingResponseItem>>, t: Throwable) {
                Log.e(TAG, "API call failed", t)
            }


        })
    }
}