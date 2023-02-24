package com.star.core.entities.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PeopleResponse(
    val count: Int? = 0, val results: List<People>? = listOf()
)

@Parcelize
data class People(
    val name: String? = "",
    val birth_year: String? = "",
    val gender: String? = "",
    val hair_color: String? = "",
    val height: String? = "",
    val homeworld: String? = "",
    val mass: String? = "",
    val skin_color: String? = "",
    val eye_color: String? = "",


    val created: String? = "",
    val edited: String? = "",
    val films: List<String>? = listOf(),
    val species: List<String>? = listOf(),
    val starships: List<String>? = listOf(),
    val vehicles: List<String>? = listOf(),
    val url: String? = ""
) : Parcelable {
    fun getFormattedYear(header: String) = "<b>${header}</b>${birth_year}"
    fun getFormattedHeight(header: String,metric:String) = "<b>${header}</b>${height}<b>${metric}</b>"


}