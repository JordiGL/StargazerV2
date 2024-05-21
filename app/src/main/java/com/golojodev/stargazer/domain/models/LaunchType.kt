package com.golojodev.stargazer.domain.models

import kotlinx.serialization.Serializable

@Serializable
sealed class LaunchType {
    object Previous : LaunchType()
    object Upcoming : LaunchType()
}