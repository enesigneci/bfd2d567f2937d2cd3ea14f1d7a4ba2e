package com.enesigneci.satellite.detail.data.model

import android.text.Spanned

data class SatelliteDetailUIModel(
    val title: String,
    val heightMass: Spanned,
    val date: String,
    val cost: Spanned
)
fun SatelliteDetail.toSatelliteDetailUIModel(title: String, heightMass: Spanned, date: String, cost: Spanned): SatelliteDetailUIModel {
    return SatelliteDetailUIModel(title, heightMass, date, cost)
}
