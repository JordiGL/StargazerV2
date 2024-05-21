package com.golojodev.stargazer

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.presentation.screens.content.ModelListItem
import org.junit.Rule
import org.junit.Test
import java.util.Date

class ModelListItemTest {
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun testModelListItem() {
//        with(composeTestRule) {
//            setContent {
//                ModelListItem(
//                    Launch(
//                        id = "1",
//                        name = "Falcon 9",
//                        status = "Success",
//                        net = Date(),
//                        pad = "Launch Pad 1",
//                        mission = "Starlink",
//                        rocket = "Falcon 9 Block 5",
//                        launchServiceProvider = "SpaceX"
//                    ),
//                    onClicked = { },
//                    onFavoriteClicked = {}
//                )
//            }
//
//            onNodeWithTag("ModelListItemCard").assertExists()
//            onNodeWithTag("ModelListItemColumn").assertExists()
//            onNodeWithTag("ModelListItemFavoriteIcon").assertExists()
//
//            onNodeWithText("fluffy").assertIsDisplayed()
//            onNodeWithContentDescription("Favorite").assertIsDisplayed()
//
//            onNodeWithTag("ModelListItemFavoriteIcon").performClick()
//        }
//    }
}