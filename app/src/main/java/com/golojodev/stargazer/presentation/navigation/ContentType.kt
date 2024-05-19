package com.golojodev.stargazer.presentation.navigation

sealed interface ContentType {
    object List : ContentType
    object ListAndDetail : ContentType
}