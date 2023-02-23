package com.star.core.entities.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PeopleResponse(
    val count: Int? = 0, val results: List<People>? = listOf()
)
@Parcelize
data class People(
    val birth_year: String? = "",
    val created: String? = "",
    val edited: String? = "",
    val eye_color: String? = "",
    val films: List<String?>? = listOf(),
    val gender: String? = "",
    val hair_color: String? = "",
    val height: String? = "",
    val homeworld: String? = "",
    val mass: String? = "",
    val name: String? = "",
    val skin_color: String? = "",
    val species: List<String?>? = listOf(),
    val starships: List<String?>? = listOf(),
    val url: String? = "",
    val vehicles: List<String?>? = listOf()
):Parcelable