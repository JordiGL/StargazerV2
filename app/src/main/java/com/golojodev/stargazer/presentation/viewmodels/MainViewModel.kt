package com.golojodev.stargazer.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.golojodev.stargazer.domain.models.Launch
import com.golojodev.stargazer.domain.states.NetworkResult
import com.golojodev.stargazer.domain.states.asResult
import com.golojodev.stargazer.domain.usecases.UseCaseProvider
import com.golojodev.stargazer.presentation.states.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Gestiona les dades a mostrar a la vista (ui)
 */
class MainViewModel(
    private val useCaseProvider: UseCaseProvider
) : ViewModel() {
    val uiState = MutableStateFlow(UIState())

    private val _favorites = MutableStateFlow<List<Launch>>(emptyList())
    val favorites: StateFlow<List<Launch>> get() = _favorites

    init {
        getModels()
    }

    fun getModels() {
        uiState.value = UIState(isLoading = true)
        viewModelScope.launch {
            useCaseProvider.onGetLaunches().asResult().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        uiState.update {
                            it.copy(isLoading = false, launches = result.data)
                        }
                    }

                    is NetworkResult.Error -> {
                        uiState.update {
                            it.copy(isLoading = false, error = result.error)
                        }
                    }
                }
            }
        }
    }

    fun fetchRemoteModels() {
        uiState.value = UIState(isLoading = true)
        viewModelScope.launch {
            useCaseProvider.onFetchLaunches().asResult().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        uiState.update {
                            it.copy(isLoading = false, launches = result.data)
                        }
                    }

                    is NetworkResult.Error -> {
                        Log.i("LAPI", result.error)
                        uiState.update {
                            it.copy(isLoading = false, error = result.error)
                        }
                    }
                }
            }
        }
    }

    fun update(launch: Launch) {
        viewModelScope.launch {
           useCaseProvider.onUpdateLaunch(launch)
        }
    }
    fun getFavorites() {
        viewModelScope.launch {
            useCaseProvider.onGetFavorites().collect { result ->
                _favorites.value = result
            }
        }
    }
}