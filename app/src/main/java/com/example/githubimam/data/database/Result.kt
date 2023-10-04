package com.example.githubimam.data.database


sealed class Result <out R> private constructor(){
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
    object Loading: Result<Nothing>()


}