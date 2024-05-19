package com.golojodev.stargazer.presentation.screens

import androidx.compose.runtime.Composable
import com.golojodev.stargazer.presentation.screens.content.SettingsScreenContent

@Composable
fun SettingsScreen(
    onThemeChange: () -> Unit = {}
) {
    SettingsScreenContent {
        onThemeChange()
    }
}