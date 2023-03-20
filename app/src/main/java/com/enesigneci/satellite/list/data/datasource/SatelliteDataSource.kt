package com.enesigneci.satellite.list.data.datasource

import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.di.coroutines.IoDispatcher
import com.enesigneci.satellite.list.data.db.SatelliteDb
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Singleton
class SatelliteDataSource @Inject constructor(
    private val satelliteDb: SatelliteDb,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun insertSatelliteList(satelliteList: SatelliteList) = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().insertSatelliteList(satelliteList)
    }

    suspend fun getSatelliteById(id: Int) = withContext(ioDispatcher) {
        prepareSatelliteDetailData(id)
    }

    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetail) = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().insertSatelliteDetail(satelliteDetail)
    }

    suspend fun prepareSatelliteDetailData(id: Int): SatelliteDetail? {
        return satelliteDb.satelliteDao().getSatelliteById(id)
    }
}
