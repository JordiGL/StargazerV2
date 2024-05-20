package com.golojodev.stargazer.presentation.screens.content

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.golojodev.stargazer.domain.models.Launch

@Composable
fun ModelList(
    modifier: Modifier,
    launches: List<Launch>,
    onFavoriteClicked: (Launch) -> Unit,
    onClicked: (Launch) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(launches) { launch ->
            ModelListItem(
                launch = launch,
                onFavoriteClicked = onFavoriteClicked,
                onClicked = onClicked
            )
        }
    }
}