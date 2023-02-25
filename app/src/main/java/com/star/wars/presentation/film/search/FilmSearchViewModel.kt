package com.star.wars.presentation.film.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.star.core.state.FilmsSearchState
import com.star.core.usecase.home.SearchFilms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmSearchViewModel @Inject constructor(
    private val searchFilms: SearchFilms
) : ViewModel() {
    private val _state = MutableStateFlow<FilmsSearchState>(FilmsSearchState.Idle)
    val state get() = _state.asStateFlow()

    val searchQuery = MutableStateFlow<String>("")

    fun searchFilm(query: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.emit(FilmsSearchState.Loading)
        _state.emit(
            try {
                val response = searchFilms.invoke(query)
                if (response.results.isNullOrEmpty())
                    FilmsSearchState.Empty
                 else FilmsSearchState.Success(response = response)
            } catch (t: Throwable) {
                FilmsSearchState.Error(t)
            }
        )

    }
}