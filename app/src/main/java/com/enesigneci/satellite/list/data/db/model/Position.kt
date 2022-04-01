package com.enesigneci.satellite.list.data.db.model


import com.google.gson.annotations.SerializedName

data class Position(
    @SerializedName("posX")
    val posX: Double? = null,
    @SerializedName("posY")
    val posY: Double? = null
)