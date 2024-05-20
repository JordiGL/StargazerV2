package com.golojodev.stargazer.domain.usecases

import com.golojodev.stargazer.domain.repositories.LaunchRepository

/**
 * Obte les dades de la API
 */
class FetchModelsUseCase(
    private val launchRepository: LaunchRepository
) {
    suspend operator fun invoke() = launchRepository.fetchRemoteLaunches()
}