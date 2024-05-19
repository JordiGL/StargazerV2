package com.golojodev.stargazer.domain.usecases

import com.golojodev.stargazer.domain.repositories.ModelRepository

/**
 * Obte les dades de la API
 */
class FetchModelsUseCase(
    private val modelRepository: ModelRepository
) {
    suspend operator fun invoke() = modelRepository.fetchRemoteModels()
}