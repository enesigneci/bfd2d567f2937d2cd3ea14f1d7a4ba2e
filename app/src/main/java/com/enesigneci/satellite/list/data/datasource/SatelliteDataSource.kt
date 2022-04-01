package com.enesigneci.satellite.list.data.datasource

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
    suspend fun listSatellites() = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().getSatellites()
    }

    suspend fun insertSatellite(satelliteList: SatelliteList) = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().insert(satelliteList)
    }
}
