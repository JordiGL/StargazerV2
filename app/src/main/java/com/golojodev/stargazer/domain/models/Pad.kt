package com.golojodev.stargazer.domain.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Pad(
    @PrimaryKey
    val id: Int,
    val name: String,
    val location: Location
)

