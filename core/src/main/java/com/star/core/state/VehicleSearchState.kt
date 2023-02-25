package com.star.core.state

import com.star.core.entities.remote.VehiclesResponse

sealed class VehicleSearchState {
    object Loading : VehicleSearchState()
    object Empty : VehicleSearchState()
    object Idle : VehicleSearchState()
    data class Error(val error: Throwable) : VehicleSearchState()
    data class Success(val response: VehiclesResponse) : VehicleSearchState()
}