package com.example.githubimam.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubimam.data.database.entity.FavoriteUserEntity


@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteUserEntity)

    @Query("DELETE FROM favoriteuserentity WHERE login = :login")
    fun deleteFavorite(login: String)

    @Query("SELECT * FROM favoriteuserentity ORDER BY login ASC")
    fun getAllFavorite(): LiveData<List<FavoriteUserEntity>>

    @Query("SELECT * FROM favoriteuserentity WHERE login = :login")
    fun getUserByLogin(login: String): LiveData<FavoriteUserEntity>


}
