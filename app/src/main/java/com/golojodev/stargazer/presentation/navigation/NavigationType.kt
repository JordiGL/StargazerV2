package com.golojodev.stargazer.presentation.navigation

sealed interface NavigationType {
    object BottomNavigation : NavigationType
    object NavigationDrawer : NavigationType
    object NavigationRail : NavigationType
}