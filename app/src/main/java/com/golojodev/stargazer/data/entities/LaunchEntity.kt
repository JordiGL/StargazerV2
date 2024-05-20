package com.golojodev.stargazer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Launch")
data class LaunchEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val status: String,
    val net: Long,
    val pad: String,
    val mission: String,
    val rocket: String,
    val launchServiceProvider: String,
    @ColumnInfo(defaultValue = "0")
    val isFavorite: Boolean = false
)
