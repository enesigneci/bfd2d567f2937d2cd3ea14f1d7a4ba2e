package com.enesigneci.satellite.list.domain

import com.enesigneci.satellite.detail.data.model.PositionList
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.list.data.db.model.SatelliteList

interface SatelliteRepository {
    suspend fun loadSatellites(): List<SatelliteList>
    suspend fun insertSatelliteList(satellite: SatelliteList)

    suspend fun getSatelliteById(id: Int, name: String): SatelliteDetail
    suspend fun getPositions(): PositionList
}