package com.golojodev.stargazer.presentation.screens.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.golojodev.stargazer.domain.models.Launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsScreenContent(modifier: Modifier, launch: Launch) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FlowRow(
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp)
        ) {
            repeat(2) {
                SuggestionChip(
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp),
                    onClick = { },
                    label = {
                        Text(text = "Tag $it")
                    }
                )
            }
        }
    }
}