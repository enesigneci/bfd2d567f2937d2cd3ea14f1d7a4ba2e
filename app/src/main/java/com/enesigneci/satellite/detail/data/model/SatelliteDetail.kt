package com.enesigneci.satellite.detail.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "satellite_detail")
data class SatelliteDetail(
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int? = null,
    @SerializedName("first_flight")
    val firstFlight: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    @SerializedName("mass")
    val mass: Int? = null
)