package com.enesigneci.satellite.detail.data.model

import com.google.gson.annotations.SerializedName

data class PositionList(
    @SerializedName("list")
    val list: List<Positions>
)
