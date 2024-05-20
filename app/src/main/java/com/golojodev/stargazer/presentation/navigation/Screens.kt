package com.golojodev.stargazer.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    object Home

    @Serializable
    data class Details(val launch: String)

    @Serializable
    object Favorite

    @Serializable
    object Settings
}