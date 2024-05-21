package com.golojodev.stargazer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Pad(
    val name: String = "",
    val location: Location
)

