package com.enesigneci.satellite.list.data

import com.enesigneci.satellite.detail.data.model.PositionList
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.list.data.datasource.SatelliteDataSource
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import com.enesigneci.satellite.list.domain.SatelliteRepository

class SatelliteRepositoryImpl constructor(
    private val dataSource: SatelliteDataSource
) : SatelliteRepository {
    override suspend fun loadSatellites(): List<SatelliteList> {
        return dataSource.listSatellites()
    }

    override suspend fun insertSatelliteList(satellite: SatelliteList) {
        dataSource.insertSatelliteList(satellite)
    }

    override suspend fun getSatelliteById(id: Int): SatelliteDetail? {
        return dataSource.getSatelliteById(id)
    }

    override suspend fun getPositions(): PositionList {
        return dataSource.getPositions()
    }
}
