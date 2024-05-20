package com.golojodev.stargazer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Location")
data class LocationEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val countryCode: String
)

