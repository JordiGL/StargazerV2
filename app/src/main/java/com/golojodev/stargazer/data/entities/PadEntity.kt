package com.golojodev.stargazer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pad")
data class PadEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val location: LocationEntity
)
