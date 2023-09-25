package com.example.githubimam.detail_user.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubimam.data.response.GithubResponse
import com.example.githubimam.data.response.ItemsItem
import com.example.githubimam.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _githubUser = MutableLiveData<List<ItemsItem>>()
    val githubUser: LiveData<List<ItemsItem>> = _githubUser



    companion object {
        private const val TAG = "MainActivity"
        private const val SEARCH = "imam"
    }

    init {
        findUser(SEARCH)
    }

    private fun findUser(search: String) {
        val client = ApiConfig.getApiService().getUsers(search)

        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(call: Call<GithubResponse>, response: Response<GithubResponse>) {
                if (response.isSuccessful) {
                        _githubUser.value = response.body()?.items
                        Log.d(TAG, response.body()?.items.toString())

                } else {
                    Log.d(TAG, "Response not successful")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.e(TAG, "API call failed", t)
            }
        })
    }

    fun searchUser(query: String) {
        val client = ApiConfig.getApiService().getUsers(query)

        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(call: Call<GithubResponse>, response: Response<GithubResponse>) {
                if (response.isSuccessful) {
                    _githubUser.value = response.body()?.items
                    Log.d(TAG, response.body()?.items.toString())
                } else {
                    Log.d(TAG, "Response not successful")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.e(TAG, "API call failed", t)
            }
        })
    }




}