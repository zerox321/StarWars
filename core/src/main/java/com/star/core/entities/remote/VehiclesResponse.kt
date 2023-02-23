package com.star.core.entities.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class VehiclesResponse(
    val count: Int? = 0, val results: List<Vehicle>? = listOf()
)

@Parcelize

data class Vehicle(
    val cargo_capacity: String? = "",
    val consumables: String? = "",
    val cost_in_credits: String? = "",
    val created: String? = "",
    val crew: String? = "",
    val edited: String? = "",
    val films: List<String?>? = listOf(),
    val length: String? = "",
    val manufacturer: String? = "",
    val max_atmosphering_speed: String? = "",
    val model: String? = "",
    val name: String? = "",
    val passengers: String? = "",
    val pilots: List<String?>? = listOf(),
    val url: String? = "",
    val vehicle_class: String? = ""
) : Parcelable