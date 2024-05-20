package com.golojodev.stargazer.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.presentation.navigation.ContentType
import com.golojodev.stargazer.presentation.screens.content.ModelList
import com.golojodev.stargazer.presentation.screens.content.ModelListAndDetails
import com.golojodev.stargazer.presentation.states.UIState

@Composable
fun ModelScreenController(
    modifier: Modifier,
    contentType: ContentType,
    uiState: UIState,
    onFavoriteClicked: (Launch) -> Unit,
    onModelClicked: (Launch) -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = uiState.isLoading
        ) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(
            visible = uiState.launches.isNotEmpty()
        ) {
            if (contentType == ContentType.List) {
                ModelList(
                    modifier = Modifier.fillMaxWidth(),
                    launches = uiState.launches,
                    onFavoriteClicked = onFavoriteClicked,
                    onClicked = onModelClicked
                )
            } else {
                ModelListAndDetails(
                    launches = uiState.launches,
                    onFavoriteClicked = onFavoriteClicked
                )
            }
        }
        AnimatedVisibility(
            visible = uiState.error != null
        ) {
            Text(text = uiState.error ?: "")
        }
    }
}