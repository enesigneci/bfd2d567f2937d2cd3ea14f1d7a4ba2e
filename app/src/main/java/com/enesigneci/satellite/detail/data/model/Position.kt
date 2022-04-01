package com.enesigneci.satellite.detail.data.model


import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("posX")
    val posX: Double? = null,
    @SerializedName("posY")
    val posY: Double? = null
)