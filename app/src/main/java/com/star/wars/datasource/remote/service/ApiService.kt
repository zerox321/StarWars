package com.star.wars.datasource.remote.service

import com.star.core.entities.remote.FilmResponse
import com.star.core.entities.remote.PeopleResponse
import com.star.core.entities.remote.VehiclesResponse
import com.star.wars.datasource.EndPoint.FilmsConstant
import com.star.wars.datasource.EndPoint.PeopleConstant
import com.star.wars.datasource.EndPoint.VehiclesConstant
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(value = FilmsConstant)
    suspend fun fetchFilms(@Query(value = "page") page: Int): FilmResponse

    @GET(value = FilmsConstant)
    suspend fun searchFilms(@Query(value = "search") query: String): FilmResponse


    @GET(value = VehiclesConstant)
    suspend fun fetchVehicles(@Query(value = "page") page: Int): VehiclesResponse
    @GET(value = VehiclesConstant)
    suspend fun searchVehicles(@Query(value = "search") query: String): VehiclesResponse
    @GET(value = PeopleConstant)
    suspend fun fetchPeople(@Query(value = "page") page: Int): PeopleResponse

    @GET(value = PeopleConstant)
    suspend fun searchPeople(@Query(value = "search") query: String): PeopleResponse
}