package com.star.core.repository

import com.star.core.entities.remote.FilmResponse
import com.star.core.entities.remote.PeopleResponse
import com.star.core.entities.remote.VehiclesResponse


interface HomeRepo {
    suspend fun fetchFilms(page: Int): FilmResponse
    suspend fun searchFilms(query:String): FilmResponse
    suspend fun fetchVehicles(page: Int): VehiclesResponse
    suspend fun searchVehicles(query: String): VehiclesResponse
    suspend fun fetchPeople(page: Int): PeopleResponse
    suspend fun searchPeople(query:String): PeopleResponse


}