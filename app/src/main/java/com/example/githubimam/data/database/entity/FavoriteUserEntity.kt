package com.example.githubimam.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FavoriteUserEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "login")
    var login: String? = null,

    @ColumnInfo(name = "avatar_url")
    var avatar_url: String? = null,


)