package com.golojodev.stargazer.domain.usecases

class UseCaseProviderImpl(
    override val onSaveTheme: SaveThemeUseCase,
    override val onGetTheme: GetThemeUseCase,
    override val onGetLaunches: GetModelsUseCase,
    override val onFetchLaunches: FetchModelsUseCase,
    override val onUpdateLaunch: UpdateModelUseCase,
    override val onGetFavorites: GetFavoritesUseCase
) : UseCaseProvider