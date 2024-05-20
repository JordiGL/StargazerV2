package com.golojodev.stargazer.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Configuration")
data class ConfigurationEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val family: String,
    val variant: String
)
