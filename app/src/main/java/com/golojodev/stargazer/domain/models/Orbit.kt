package com.golojodev.stargazer.domain.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Orbit(
    @PrimaryKey
    val id: Int,
    val name: String
)
