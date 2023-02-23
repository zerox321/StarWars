package com.star.core.usecase.home

import com.star.core.entities.remote.VehiclesResponse
import com.star.core.repository.HomeRepo


class GetVehicles(private val repo: HomeRepo) {
    suspend operator fun invoke(page: Int) : VehiclesResponse = repo.fetchVehicles(page = page)
}