package com.example.githubimam.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubimam.data.database.entity.FavoriteUserEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class FavoriteUserRepository(
    application: Application
){
private val mFavoriteUserDao: FavoriteDAO
private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteDao()
    }

    fun getAllFavorites(): LiveData<List<FavoriteUserEntity>> = mFavoriteUserDao.getAllFavorite()

    fun insert(user: FavoriteUserEntity) {
        executorService.execute { mFavoriteUserDao.insertFavorite(user) }
    }

    fun getUserByLogin(login: String): LiveData<FavoriteUserEntity> {
        return mFavoriteUserDao.getUserByLogin(login)
    }




    fun delete(login: String) {
        executorService.execute { mFavoriteUserDao.deleteFavorite(login) }
    }







}