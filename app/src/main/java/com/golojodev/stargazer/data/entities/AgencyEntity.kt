package com.golojodev.stargazer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Agency")
data class AgencyEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String
)
