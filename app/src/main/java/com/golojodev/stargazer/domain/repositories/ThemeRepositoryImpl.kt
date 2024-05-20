package com.golojodev.stargazer.domain.repositories

import com.golojodev.library.style.ThemeState
import com.golojodev.stargazer.data.ThemeStateDao
import com.golojodev.stargazer.data.ThemeStateEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ThemeRepositoryImpl(
    private val themeDao: ThemeStateDao,
    private val dispatcher: CoroutineDispatcher
) : ThemeRepository {
    override suspend fun getTheme(): Flow<ThemeState> {
        return withContext(dispatcher) {
            themeDao.getThemeState().map {
                it.theme
            }
        }
    }

    override suspend fun saveTheme(themeState: ThemeState) {
        withContext(dispatcher) {
            themeDao.update(ThemeStateEntity(id = 0, theme = themeState))
        }
    }
}