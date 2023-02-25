package com.star.core.state

import com.star.core.entities.remote.FilmResponse

sealed class FilmsSearchState {
    object Loading : FilmsSearchState()
    object Empty : FilmsSearchState()
    object Idle : FilmsSearchState()
    data class Error(val error: Throwable) : FilmsSearchState()
    data class Success(val response: FilmResponse) : FilmsSearchState()
}