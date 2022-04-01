package com.enesigneci.satellite.list.domain

import com.enesigneci.satellite.list.data.db.model.SatelliteList

interface SatelliteRepository {
    suspend fun loadSatellites(): List<SatelliteList>
    suspend fun insertSatellite(satellite: SatelliteList)
}