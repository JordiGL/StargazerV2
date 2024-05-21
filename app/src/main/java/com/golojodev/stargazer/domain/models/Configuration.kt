package com.golojodev.stargazer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    val name: String = "",
    val family: String = "",
    val variant: String = ""
)

