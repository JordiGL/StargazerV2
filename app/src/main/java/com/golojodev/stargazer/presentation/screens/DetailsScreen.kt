package com.golojodev.stargazer.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.presentation.screens.content.DetailsScreenContent

@Composable
fun DetailsScreen(launch: Launch) {
    Scaffold(
        content = { paddingValues ->
            DetailsScreenContent(
                modifier = Modifier.padding(paddingValues),
                launch = launch
            )
        }
    )
}