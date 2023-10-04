package com.example.githubimam.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubimam.data.database.entity.FavoriteUserEntity

@Database(entities = [FavoriteUserEntity::class], version = 1)


abstract class FavoriteDatabase: RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDAO

    companion object{
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteDatabase::class.java, "favorite_database")
                        .build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }

    }
}
