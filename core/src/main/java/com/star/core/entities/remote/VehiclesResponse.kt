package com.star.core.entities.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class VehiclesResponse(
    val count: Int? = 0, val results: List<Vehicle>? = listOf()
)

@Parcelize

data class Vehicle(
    val vehicle_class: String? = "",
    val cargo_capacity: String? = "",
    val consumables: String? = "",
    val crew: String? = "",
    val length: String? = "",
    val manufacturer: String? = "",
    val cost_in_credits: String? = "",
    val passengers: String? = "",
    val max_atmosphering_speed: String? = "",

    val created: String? = "",
    val edited: String? = "",
    val films: List<String>? = listOf(),
    val model: String? = "",
    val name: String? = "",
    val pilots: List<String>? = listOf(),
    val url: String? = "",
) : Parcelable{
    fun getFormattedModel(header:String)="<b>${header}</b>${model}"
    fun getFormattedConsumables(header:String)="<b>${header}</b>${consumables}"
    fun getFormattedCarClass(header:String)="<b>${header}</b>${vehicle_class}"
    fun getFormattedPassengers(header:String)="<b>${header}</b>${passengers}"

}