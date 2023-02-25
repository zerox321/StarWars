package com.star.wars.presentation.vehicle.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.star.core.state.PeopleSearchState
import com.star.core.state.VehicleSearchState
import com.star.core.usecase.home.SearchVehicles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleSearchViewModel @Inject constructor(
    private val searchVehicles: SearchVehicles
) : ViewModel() {
    val searchQuery = MutableStateFlow<String>("")

    private val _state = MutableStateFlow<VehicleSearchState>(VehicleSearchState.Idle)
    val state get() = _state.asStateFlow()

    fun searchVehicles(query: String) = viewModelScope.launch(Dispatchers.IO) {
        _state.emit(VehicleSearchState.Loading)
        _state.emit(
            try {
                val response = searchVehicles.invoke(query)
                if (response.results.isNullOrEmpty()) {
                    PeopleSearchState.Empty
                    return@launch
                }
                VehicleSearchState.Success(response = response)
            } catch (t: Throwable) {
                VehicleSearchState.Error(t)
            }
        )
    }
}