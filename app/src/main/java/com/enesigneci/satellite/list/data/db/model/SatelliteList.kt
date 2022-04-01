package com.enesigneci.satellite.list.data.db.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "satellites")
data class SatelliteList(
    @SerializedName("active")
    val active: Boolean? = null,
    @SerializedName("id")
    @PrimaryKey
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)