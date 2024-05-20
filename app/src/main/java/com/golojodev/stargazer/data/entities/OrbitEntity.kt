package com.golojodev.stargazer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Orbit")
data class OrbitEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)