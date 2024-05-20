package com.golojodev.stargazer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.golojodev.library.style.ThemeStateManager
import com.golojodev.stargazer.presentation.screens.DetailsScreen
import com.golojodev.stargazer.presentation.screens.FavoriteScreen
import com.golojodev.stargazer.presentation.screens.HomeScreen
import com.golojodev.stargazer.presentation.screens.SettingsScreen
import com.golojodev.stargazer.presentation.viewmodels.ThemeViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    contentType: ContentType,
    navController: NavHostController = rememberNavController(),
    themeViewModel: ThemeViewModel = koinViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home
    ) {
        composable<Screens.Home> {
            HomeScreen(
                contentType = contentType,
                onModelClicked = {
                    navController.navigate(
                        Screens.Details(Json.encodeToString(it))
                    )
                }
            )
        }
        composable<Screens.Details> {
            DetailsScreen(
                launch = Json.decodeFromString(it.toRoute<Screens.Details>().launch)
            )
        }
        composable<Screens.Favorite> {
            FavoriteScreen(
                onClicked = {
                    navController.navigate(
                        Screens.Details(Json.encodeToString(it))
                    )
                }
            )
        }
        composable<Screens.Settings> {
            SettingsScreen(
                onThemeChange = {
                    ThemeStateManager.toggle {
                        themeViewModel.setTheme(it)
                    }
                }
            )
        }
    }
}