package com.golojodev.stargazer.presentation.screens.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.golojodev.stargazer.domain.models.Launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ModelListItem(
    launch: Launch,
    onFavoriteClicked: (Launch) -> Unit,
    onClicked: (Launch) -> Unit = {}
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .testTag("ModelListItemCard")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clickable {
                    onClicked(launch)
                }
                .testTag("ModelListItemColumn")
        ) {
            Text(text = launch.id)
            Text(text = launch.name)
            FlowRow(
                modifier = Modifier
                    .padding(start = 6.dp, end = 6.dp)
            ) {
//                repeat(launch.tags.size) {
//                    SuggestionChip(
//                        modifier = Modifier
//                            .padding(
//                                start = 3.dp,
//                                end =
//                                3.dp
//                            ),
//                        onClick = { },
//                        label = {
//                            Text(text = launch.tags[it])
//                        }
//                    )
//                }
            }
            Icon(
                modifier = Modifier
                    .testTag("ModelListItemFavoriteIcon")
                    .clickable {
                        onFavoriteClicked(launch.copy(isFavorite = !launch.isFavorite))
                    },
                imageVector = if (launch.isFavorite) {
                    Icons.Default.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = "Favorite",
                tint = if (launch.isFavorite) {
                    Color.Red
                } else {
                    Color.Gray
                }
            )
        }
    }
}