package com.enesigneci.satellite.list.data

import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.di.coroutines.IoDispatcher
import com.enesigneci.satellite.list.data.datasource.AssetDataSource
import com.enesigneci.satellite.list.data.datasource.SatelliteDataSource
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import com.enesigneci.satellite.list.domain.SatelliteRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SatelliteRepositoryImpl @Inject constructor(
    private val satelliteDataSource: SatelliteDataSource,
    private val assetDataSource: AssetDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : SatelliteRepository {
    override suspend fun loadSatellites(): List<SatelliteList> {
        return assetDataSource.populateSatelliteDatabase()
    }

    override suspend fun insertSatelliteList(satellite: SatelliteList) {
        satelliteDataSource.insertSatelliteList(satellite)
    }

    override suspend fun getSatelliteById(id: Int): SatelliteDetail? {
        return if (satelliteDataSource.getSatelliteById(id) != null) {
            satelliteDataSource.getSatelliteById(id)
        } else {
            withContext(ioDispatcher) {
                val satelliteDetailList = assetDataSource.populateSatelliteDetailDatabase()
                satelliteDetailList.find { it.id == id }
                    ?.let { satelliteDataSource.insertSatelliteDetail(it) }
            }
            satelliteDataSource.getSatelliteById(id)
        }
    }
}
