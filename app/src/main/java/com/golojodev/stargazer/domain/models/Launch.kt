package com.golojodev.stargazer.domain.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class Launch(
    @PrimaryKey
    val id: String,
    val name: String,
    val status: String,
    @Serializable(with = DateSerializer::class)
    val net: Date,
    val pad: String,
    val mission: String,
    val rocket: String,
    val launchServiceProvider: String,
    val isFavorite: Boolean = false
)