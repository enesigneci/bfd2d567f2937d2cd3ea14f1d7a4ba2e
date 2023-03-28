package com.enesigneci.satellite.detail.data.model

import android.text.Spanned
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.enesigneci.satellite.R
import com.enesigneci.satellite.base.StringProvider

data class SatelliteDetailUIModel(
    val title: String,
    val heightMass: Spanned,
    val date: String,
    val cost: Spanned
)
fun SatelliteDetail.toSatelliteDetailUIModel(stringProvider: StringProvider, title: String, date: String, cost: Spanned): SatelliteDetailUIModel {
    return SatelliteDetailUIModel(title,
        buildSpannedString {
            bold {
                append(stringProvider.getString(R.string.height_mass))
            }
            append("$height / $mass")
        },
        date,
        cost)
}
