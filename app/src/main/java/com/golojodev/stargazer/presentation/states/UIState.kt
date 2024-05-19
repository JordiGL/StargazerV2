package com.golojodev.stargazer.presentation.states

import com.golojodev.stargazer.domain.models.Model

data class UIState(
    val isLoading: Boolean = false,
    val models: List<Model> = emptyList(),
    val error: String? = null
)