package com.golojodev.stargazer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Status")
data class StatusEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String
)

