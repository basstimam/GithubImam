package com.example.githubimam.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.githubimam.data.database.entity.FavoriteUserEntity


@Dao
interface FavoriteDAO{

    @Insert
    fun insertFavorite(favorite: FavoriteUserEntity)

    @Delete
    fun deleteFavorite(favorite: FavoriteUserEntity)

    @Query("SELECT * FROM favoriteuserentity")
    fun getAllFavorite(): List<FavoriteUserEntity>
}