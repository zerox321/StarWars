package com.star.core.usecase.home

import com.star.core.entities.remote.VehiclesResponse
import com.star.core.repository.HomeRepo


class SearchVehicles(private val repo: HomeRepo) {
    suspend operator fun invoke(query: String) : VehiclesResponse = repo.searchVehicles(query = query)
}