package com.golojodev.stargazer.domain.usecases

import com.golojodev.stargazer.domain.repositories.LaunchRepository

class GetFavoritesUseCase(
    private val launchRepository: LaunchRepository
) {
    suspend operator fun invoke() = launchRepository.getFavorites()
}