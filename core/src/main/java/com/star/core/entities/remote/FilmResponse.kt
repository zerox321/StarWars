package com.star.core.entities.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class FilmResponse(
    val count: Int? = 0, val results: List<Film>? = listOf()
)

@Parcelize
data class Film(
    val created: String? = "",
    val director: String? = "",
    val edited: String? = "",
    val episode_id: Int? = 0,
    val opening_crawl: String? = "",
    val producer: String? = "",
    val release_date: String? = "",
    val title: String? = "",
    val url: String? = "",
    val planets: List<String?>? = listOf(),
    val characters: List<String?>? = listOf(),
    val species: List<String?>? = listOf(),
    val starships: List<String?>? = listOf(),
    val vehicles: List<String?>? = listOf()
) : Parcelable
