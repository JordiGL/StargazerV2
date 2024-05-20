package com.golojodev.stargazer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Rocket")
data class RocketEntity(
    @PrimaryKey
    val id: Int,
    val configuration: ConfigurationEntity
)
