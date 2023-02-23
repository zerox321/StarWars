package com.star.core.entities.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class StarshipsResponse(
    val count: Int? = 0, val results: List<Starship>? = listOf()
)
@Parcelize

data class Starship(
    val MGLT: String? = "",
    val cargo_capacity: String? = "",
    val consumables: String? = "",
    val cost_in_credits: String? = "",
    val created: String? = "",
    val crew: String? = "",
    val edited: String? = "",
    val films: List<String?>? = listOf(),
    val hyperdrive_rating: String? = "",
    val length: String? = "",
    val manufacturer: String? = "",
    val max_atmosphering_speed: String? = "",
    val model: String? = "",
    val name: String? = "",
    val passengers: String? = "",
    val pilots: List<String?>? = listOf(),
    val starship_class: String? = "",
    val url: String? = ""
):Parcelable