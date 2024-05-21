package com.golojodev.stargazer.domain.models

import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Launch(
    @PrimaryKey
    val id: String,
    val name: String = "",
    val status: Status = Status(),
    val net: String = "",
    val pad: Pad = Pad(location = Location()),
    val mission: Mission = Mission(),
    val rocket: Rocket = Rocket(Configuration()),
    @SerialName("launch_service_provider")
    val launchServiceProvider: Agency = Agency(),
    val launchType: LaunchType = LaunchType.Upcoming,
    val isFavorite: Boolean = false
)