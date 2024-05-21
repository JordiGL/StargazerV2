package com.golojodev.stargazer.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String = "",
    @SerialName("country_code")
    val countryCode: String = ""
)
