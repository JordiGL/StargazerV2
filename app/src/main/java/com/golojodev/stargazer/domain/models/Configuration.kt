package com.golojodev.stargazer.domain.models

import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    @PrimaryKey
    val id: Int,
    val name: String,
    val family: String,
    val variant: String
)

