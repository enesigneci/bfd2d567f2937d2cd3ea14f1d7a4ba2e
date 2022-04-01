package com.enesigneci.satellite.list.data.datasource

import com.enesigneci.satellite.list.data.db.model.SatelliteList
import com.enesigneci.satellite.list.domain.SatelliteRepository

class SatelliteRepositoryImpl constructor(
    private val dataSource: SatelliteDataSource
) : SatelliteRepository {
    override suspend fun loadSatellites(): List<SatelliteList> {
        return dataSource.listSatellites()
    }

    override suspend fun insertSatellite(satellite: SatelliteList) {
        dataSource.insertSatellite(satellite)
    }
}
