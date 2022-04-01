package com.enesigneci.satellite.list.data.db.model


import com.google.gson.annotations.SerializedName

data class SatelliteList(
    @SerializedName("active")
    val active: Boolean? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)