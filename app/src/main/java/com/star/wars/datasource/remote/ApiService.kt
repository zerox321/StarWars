package com.star.wars.datasource.remote

import com.star.core.entities.remote.FilmResponse
import com.star.core.entities.remote.PeopleResponse
import com.star.core.entities.remote.StarshipsResponse
import com.star.core.entities.remote.VehiclesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    https://swapi.dev/api/people/?search=r2
//    {
//        "films": "https://swapi.dev/api/films/",
//        "people": "https://swapi.dev/api/people/",
//        "planets": "https://swapi.dev/api/planets/",
//        "species": "https://swapi.dev/api/species/",
//        "starships": "https://swapi.dev/api/starships/",
//        "vehicles": "https://swapi.dev/api/vehicles/"
//    }

    @GET("films/")
    suspend fun fetchFilms(@Query(value = "page") page: Int): FilmResponse

    @GET("vehicles/")
    suspend fun fetchVehicles(@Query(value = "page") page: Int): VehiclesResponse

    @GET("starships/")
    suspend fun fetchStarships(@Query(value = "page") page: Int): StarshipsResponse

    @GET("people/")
    suspend fun fetchPeople(@Query(value = "page") page: Int): PeopleResponse

}