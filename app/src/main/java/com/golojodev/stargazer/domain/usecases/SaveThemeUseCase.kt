package com.golojodev.stargazer.domain.usecases

import com.golojodev.library.style.ThemeState
import com.golojodev.stargazer.domain.repositories.ThemeRepository

/**
 * UseCase per recuperar el [ThemeState]
 *
 * @param themeRepository [ThemeRepository]
 */
class SaveThemeUseCase(
    private val themeRepository: ThemeRepository
) {
    suspend operator fun invoke(themeState: ThemeState) {
        themeRepository.saveTheme(themeState)
    }
}