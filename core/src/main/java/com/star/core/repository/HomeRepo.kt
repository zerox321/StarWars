package com.star.core.repository

import com.star.core.entities.remote.FilmResponse
import com.star.core.entities.remote.PeopleResponse
import com.star.core.entities.remote.StarshipsResponse
import com.star.core.entities.remote.VehiclesResponse


interface HomeRepo {
    suspend fun fetchFilms(page: Int): FilmResponse
    suspend fun fetchVehicles(page: Int): VehiclesResponse
    suspend fun fetchStarships(page: Int): StarshipsResponse
    suspend fun fetchPeople(page: Int): PeopleResponse

}