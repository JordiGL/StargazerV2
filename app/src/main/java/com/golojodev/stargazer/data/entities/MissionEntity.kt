package com.golojodev.stargazer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Mission")
data class MissionEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val orbit: OrbitEntity
)
