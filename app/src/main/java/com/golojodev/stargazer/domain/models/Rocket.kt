package com.golojodev.stargazer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Rocket(
    val configuration: Configuration
)
