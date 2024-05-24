package com.golojodev.stargazer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.golojodev.library.style.ThemeStateManager
import com.golojodev.stargazer.data.workers.ModelsSyncWorker
import com.golojodev.stargazer.presentation.navigation.AppNavigationContent
import com.golojodev.stargazer.presentation.navigation.ContentType
import com.golojodev.stargazer.presentation.navigation.DeviceFoldPosture
import com.golojodev.stargazer.presentation.navigation.NavigationType
import com.golojodev.stargazer.presentation.navigation.Screens
import com.golojodev.stargazer.presentation.navigation.isBookPosture
import com.golojodev.stargazer.presentation.navigation.isSeparating
import com.golojodev.stargazer.presentation.screens.content.CustomNavigationDrawer
import com.golojodev.stargazer.presentation.viewmodels.MainViewModel
import com.golojodev.stargazer.presentation.viewmodels.ThemeViewModel
import com.golojodev.stargazer.ui.theme.ApitemplateTheme
import com.golojodev.library.timecontrol.TimeControlHandler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration.Companion.minutes

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startModelsSync()
        val deviceFoldingPostureFlow = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature =
                    layoutInfo.displayFeatures
                        .filterIsInstance<FoldingFeature>()
                        .firstOrNull()
                when {
                    isBookPosture(foldingFeature) ->
                        DeviceFoldPosture.BookPosture(foldingFeature.bounds)
                    isSeparating(foldingFeature) ->
                        DeviceFoldPosture.SeparatingPosture(
                            foldingFeature.bounds,
                            foldingFeature.orientation
                        )
                    else -> DeviceFoldPosture.NormalPosture
                }
            }
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DeviceFoldPosture.NormalPosture
            )
        setContent {
            val devicePosture = deviceFoldingPostureFlow.collectAsStateWithLifecycle().value
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val navController = rememberNavController()
            val themeViewModel: ThemeViewModel = koinViewModel()
            val mainViewModel: MainViewModel = koinViewModel()

            ThemeStateManager.init(
                state = themeViewModel.themeState.collectAsStateWithLifecycle(),
                isDark = isSystemInDarkTheme()
            )

            TimeControlHandler.init()

            ApitemplateTheme(darkTheme = ThemeStateManager.isDark()) {
                val navigationType: NavigationType
                val contentType: ContentType
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        navigationType = NavigationType.NavigationRail
                        contentType = ContentType.List
                    }
                    WindowWidthSizeClass.Medium -> {
                        navigationType = NavigationType.NavigationRail
                        contentType = if (devicePosture is DeviceFoldPosture
                            .BookPosture ||
                            devicePosture is DeviceFoldPosture
                            .SeparatingPosture
                        ) {
                            ContentType.ListAndDetail
                        } else {
                            ContentType.List
                        }
                    }
                    WindowWidthSizeClass.Expanded -> {
                        navigationType = if (devicePosture is DeviceFoldPosture
                            .BookPosture
                        ) {
                            NavigationType.NavigationRail
                        } else {
                            NavigationType.NavigationDrawer
                        }
                        contentType = ContentType.ListAndDetail
                    }
                    else -> {
                        navigationType = NavigationType.BottomNavigation
                        contentType = ContentType.List
                    }
                }
                if (navigationType == NavigationType.NavigationDrawer) {
                    PermanentNavigationDrawer(
                        drawerContent = {
                            PermanentDrawerSheet {
                                CustomNavigationDrawer(
                                    onFavoriteClicked = {
                                        navController.navigate(Screens.Favorite)
                                    },
                                    onHomeClicked = {
                                        navController.navigate(Screens.Settings)
                                    }
                                )
                            }
                        }
                    ) {
                        AppNavigationContent(
                            contentType = contentType,
                            navigationType = navigationType,
                            onFavoriteClicked = {
                                navController.navigate(Screens.Favorite)
                            },
                            onHomeClicked = {
                                navController.navigate(Screens.Home)
                            },
                            onSettingsClicked = {
                                navController.navigate(Screens.Settings)
                            },
                            onRefreshClicked = {
                                if (TimeControlHandler.hasTimePassed(duration = 1.minutes)) {
                                    mainViewModel.fetchRemoteModels()
                                }
                            },
                            navController = navController
                        )
                    }
                } else {
                    ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet {
                                CustomNavigationDrawer(
                                    onFavoriteClicked = {
                                        navController.navigate(Screens.Favorite)
                                    },
                                    onHomeClicked = {
                                        navController.navigate(Screens.Home)
                                    },
                                    onDrawerClicked = {
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                                )
                            }
                        },
                        drawerState = drawerState
                    ) {
                        AppNavigationContent(
                            contentType = contentType,
                            navigationType = navigationType,
                            onFavoriteClicked = {
                                navController.navigate(Screens.Favorite)
                            },
                            onHomeClicked = {
                                navController.navigate(Screens.Home)
                            },
                            onSettingsClicked = {
                                navController.navigate(Screens.Settings)
                            },
                            onRefreshClicked = {
                                if (TimeControlHandler.hasTimePassed(duration = 1.minutes)) {
                                    mainViewModel.fetchRemoteModels()
                                }
                            },
                            navController = navController,
                            onDrawerClicked = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    private fun startModelsSync() {
        val syncModelsWorkRequest =
            OneTimeWorkRequestBuilder<ModelsSyncWorker>()
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresBatteryNotLow(true)
                        .build()
                )
                .build()
        WorkManager.getInstance(applicationContext)
            .enqueueUniqueWork(
                "ModelsSyncWorker",
                ExistingWorkPolicy.KEEP,
                syncModelsWorkRequest
            )
    }
}