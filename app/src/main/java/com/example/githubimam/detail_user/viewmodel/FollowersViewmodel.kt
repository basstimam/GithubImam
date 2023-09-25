package com.example.githubimam.detail_user.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubimam.data.response.DetailUserResponse
import com.example.githubimam.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewmodel: ViewModel() {

    private val _followers = MutableLiveData<DetailUserResponse>()
    val followers: LiveData<DetailUserResponse> = _followers

    companion object{
        private const val TAG = "FollowersViewModel"
    }



    internal fun getDetailUser(getUsername: String){
        val client = ApiConfig.getApiService().getDetailUser(getUsername)

        client.enqueue(object: Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful){
                    _followers.value = response.body()
                }else
                {
                    Log.d(TAG, "Response not successful")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.e(TAG, "API call failed", t)
            }

        })
    }

}