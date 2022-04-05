package com.enesigneci.satellite.list.data

import android.content.Context
import android.util.Log
import com.enesigneci.satellite.base.Constants
import com.enesigneci.satellite.detail.data.model.PositionList
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class DataManager @Inject constructor(
        @ApplicationContext private val context: Context,
        private val gson: Gson
    ) {
    private val TAG = "DataManager"
    private fun loadJsonFromAssets(fileName: String): String {
        try {
            val stream: InputStream = context.assets.open(fileName)
            return stream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
        return ""
    }
    fun parseJson(fileName: String): Any? {
        val json = loadJsonFromAssets(fileName)
        return when(fileName) {
            Constants.SATELLITE_LIST_JSON -> {
                val satelliteListType: Type = object : TypeToken<List<SatelliteList>>() {}.type
                gson.fromJson<List<SatelliteList>>(json, satelliteListType)
            }
            Constants.SATELLITE_DETAIL_JSON -> {
                val satelliteDetailType: Type = object : TypeToken<List<SatelliteDetail>>() {}.type
                gson.fromJson<List<SatelliteDetail>>(json, satelliteDetailType)
            }
            Constants.POSITIONS_JSON -> {
                gson.fromJson(json, PositionList::class.java)
            }
            else -> null
        }
    }
}