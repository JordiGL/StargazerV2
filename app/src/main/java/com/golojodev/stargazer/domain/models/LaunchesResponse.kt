package com.golojodev.stargazer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LaunchesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Launch>
)