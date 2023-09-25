package com.example.githubimam.detail_user.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubimam.data.response.DetailUserResponse
import com.example.githubimam.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {

    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser: MutableLiveData<DetailUserResponse> = _detailUser

    companion object{
private const val TAG = "DetailUserViewModel"
    }



    internal fun getDetailUser(getUsername: String){
        val client = ApiConfig.getApiService().getDetailUser(getUsername)

        client.enqueue(object: Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful){
                    _detailUser.value = response.body()
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