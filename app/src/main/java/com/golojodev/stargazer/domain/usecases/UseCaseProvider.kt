package com.golojodev.stargazer.domain.usecases

interface UseCaseProvider {
    val onSaveTheme: SaveThemeUseCase
    val onGetTheme: GetThemeUseCase
    val onGetLaunches: GetModelsUseCase
    val onFetchLaunches: FetchModelsUseCase
    val onUpdateLaunch: UpdateModelUseCase
    val onGetFavorites: GetFavoritesUseCase
}