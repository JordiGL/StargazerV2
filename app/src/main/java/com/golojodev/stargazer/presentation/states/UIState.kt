package com.golojodev.stargazer.presentation.states

import com.golojodev.stargazer.domain.models.Launch

data class UIState(
    val isLoading: Boolean = false,
    val launches: List<Launch> = emptyList(),
    val error: String? = null
)