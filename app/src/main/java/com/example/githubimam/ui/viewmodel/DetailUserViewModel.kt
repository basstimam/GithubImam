package com.example.githubimam.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubimam.data.database.FavoriteUserRepository
import com.example.githubimam.data.database.entity.FavoriteUserEntity
import com.example.githubimam.data.response.DetailUserResponse
import com.example.githubimam.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(mApplication: Application) : ViewModel() {

    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUser

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _userAvatarUrl = MutableLiveData<String>()
    val userAvatarUrl: LiveData<String> = _userAvatarUrl


    companion object {
        private const val TAG = "DetailUserViewModel"
    }

    private val favoriteUserRepository: FavoriteUserRepository =
        FavoriteUserRepository(mApplication)

    fun insert(user: FavoriteUserEntity) {
        favoriteUserRepository.insert(user)
    }

    fun getUserByLogin(login: String): LiveData<FavoriteUserEntity> {
        return favoriteUserRepository.getUserByLogin(login)
    }



    fun delete(login: String) {
        favoriteUserRepository.delete(login)
    }



    internal fun getDetailUser(getUsername: String) {
        val client = ApiConfig.getApiService().getDetailUser(getUsername)

        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                if (response.isSuccessful) {
                    _detailUser.value = response.body()
                } else {
                    Log.d(TAG, "Response not successful")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Toast.makeText(null, "Can't get detail user", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun setData(userName: String, userAvatarUrl: String) {
        _userName.value = userName
        _userAvatarUrl.value = userAvatarUrl
    }


}