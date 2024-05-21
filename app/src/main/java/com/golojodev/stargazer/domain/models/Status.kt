package com.golojodev.stargazer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Status(
    val name: String = "",
    val description: String = ""
)