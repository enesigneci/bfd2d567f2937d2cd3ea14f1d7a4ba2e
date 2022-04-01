package com.enesigneci.satellite.detail.data.model


import com.google.gson.annotations.SerializedName

data class SatelliteDetail(
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int? = null,
    @SerializedName("first_flight")
    val firstFlight: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("mass")
    val mass: Int? = null
)