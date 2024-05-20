package com.golojodev.stargazer.domain.usecases

import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.domain.repositories.LaunchRepository
import com.golojodev.stargazer.domain.repositories.ModelRepository

/**
 * Actualitza el model a la base de dades
 *
 * @param modelRepository [ModelRepository]
 */
class UpdateModelUseCase(
    private val launchRepository: LaunchRepository
) {
    suspend operator fun invoke(launch: Launch) {
        launchRepository.updateLaunch(launch)
    }
}