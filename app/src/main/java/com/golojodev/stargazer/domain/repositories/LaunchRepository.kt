package com.golojodev.stargazer.domain.repositories

import com.golojodev.stargazer.domain.models.Launch
import kotlinx.coroutines.flow.Flow

/**
 * Fa d'enllaç entre el ViewModel i el servei de la API
 *
 * @author golojodev
 */
interface LaunchRepository {
    suspend fun getlaunches(): Flow<List<Launch>>
    suspend fun fetchRemoteLaunches(): Flow<List<Launch>>
    suspend fun updateLaunch(launch: Launch)
    suspend fun getFavorites(): Flow<List<Launch>>
}