package com.example.githubimam.data.retrofit


import com.example.githubimam.data.response.DetailUserResponse
import com.example.githubimam.data.response.FollowersResponseItem
import com.example.githubimam.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

//    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun getUsers(
        @Query("q") q: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<FollowersResponseItem>>


}