package com.golojodev.stargazer.domain.repositories

import com.golojodev.stargazer.domain.models.Model
import kotlinx.coroutines.flow.Flow

/**
 * Fa d'enllaç entre el ViewModel i el servei de la API
 *
 * @author golojodev
 */
interface ModelRepository {
    suspend fun getModels(): Flow<List<Model>>
    suspend fun fetchRemoteModels(): Flow<List<Model>>
    suspend fun updateModel(model: Model)
    suspend fun getFavorites(): Flow<List<Model>>
}