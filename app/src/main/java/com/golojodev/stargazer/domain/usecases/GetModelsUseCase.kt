package com.golojodev.stargazer.domain.usecases

import com.golojodev.stargazer.domain.repositories.ModelRepository

/**
 * Obte les dades de la base de dades o la API si escau
 *
 * @param modelRepository [ModelRepository]
 */
class GetModelsUseCase(
    private val modelRepository: ModelRepository
) {
    suspend operator fun invoke() = modelRepository.getModels()
}