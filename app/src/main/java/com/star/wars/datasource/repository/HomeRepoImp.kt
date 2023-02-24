package com.star.wars.datasource.repository

import com.star.core.entities.remote.FilmResponse
import com.star.core.entities.remote.PeopleResponse
import com.star.core.entities.remote.StarshipsResponse
import com.star.core.entities.remote.VehiclesResponse
import com.star.core.repository.HomeRepo
import com.star.wars.datasource.remote.ApiService
import javax.inject.Inject

class HomeRepoImp @Inject constructor(private val service: ApiService) : HomeRepo {
    override suspend fun fetchFilms(page: Int): FilmResponse = service.fetchFilms(page = page)
    override suspend fun fetchVehicles(page: Int): VehiclesResponse =
        service.fetchVehicles(page = page)

    override suspend fun fetchStarships(page: Int): StarshipsResponse =
        service.fetchStarships(page = page)

    override suspend fun fetchPeople(page: Int): PeopleResponse = service.fetchPeople(page = page)
}