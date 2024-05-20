package com.golojodev.stargazer.domain.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    @PrimaryKey
    val id: Int,
    val configuration: Configuration
)
