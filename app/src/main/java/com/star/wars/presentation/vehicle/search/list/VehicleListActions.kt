package com.star.wars.presentation.vehicle.search.list

import com.star.core.entities.remote.Vehicle

interface VehicleListActions {
    fun onVehicleClick(vehicle: Vehicle?)
}