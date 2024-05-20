package com.golojodev.stargazer.domain.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Mission(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val orbit: Orbit
)
