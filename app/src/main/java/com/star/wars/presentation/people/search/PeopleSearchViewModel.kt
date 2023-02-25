package com.star.wars.presentation.people.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.star.core.state.PeopleSearchState
import com.star.core.usecase.home.SearchPeople
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PeopleSearchViewModel @Inject constructor(
    private val searchPeople: SearchPeople
) : ViewModel() {
    val searchQuery = MutableStateFlow<String>("")

    private val _state = MutableStateFlow<PeopleSearchState>(PeopleSearchState.Idle)
    val state get() = _state.asStateFlow()
    fun searchPeople(query: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.emit(PeopleSearchState.Loading)
        _state.emit(
            try {
                val response = searchPeople.invoke(query)
                if (response.results.isNullOrEmpty()) {
                    PeopleSearchState.Empty
                    return@launch
                }
                PeopleSearchState.Success(response = response)
            } catch (t: Throwable) {
                PeopleSearchState.Error(t)
            }
        )
    }
}