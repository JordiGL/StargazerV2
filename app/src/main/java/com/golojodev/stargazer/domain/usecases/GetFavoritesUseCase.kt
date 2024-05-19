package com.golojodev.stargazer.domain.usecases

import com.golojodev.stargazer.domain.repositories.ModelRepository

class GetFavoritesUseCase(
    private val modelRepository: ModelRepository
) {
    suspend operator fun invoke() = modelRepository.getFavorites()
}