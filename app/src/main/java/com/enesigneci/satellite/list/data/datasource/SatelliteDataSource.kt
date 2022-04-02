package com.enesigneci.satellite.list.data.datasource

import com.enesigneci.satellite.base.Constants
import com.enesigneci.satellite.di.coroutines.IoDispatcher
import com.enesigneci.satellite.list.data.DataManager
import com.enesigneci.satellite.list.data.db.SatelliteDb
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Singleton
class SatelliteDataSource @Inject constructor(
    private val satelliteDb: SatelliteDb,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val dataManager: DataManager
) {
    suspend fun listSatellites() = withContext(ioDispatcher) {
        prepareSatelliteData()
    }

    suspend fun insertSatellite(satelliteList: SatelliteList) = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().insert(satelliteList)
    }

    suspend fun insertAllSatellites(list: List<SatelliteList>) = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().insertAll(list)
    }

    private suspend fun prepareSatelliteData(): List<SatelliteList> {
        val satelliteListFromDb = satelliteDb.satelliteDao().getSatellites()
        return if (satelliteListFromDb.isEmpty()) {
            val satellites = populateSatelliteDatabase()
            insertAllSatellites(satellites)
            satellites
        } else {
            satelliteListFromDb
        }
    }

    private fun populateSatelliteDatabase(): List<SatelliteList> {
        return dataManager.parseJson(Constants.SATELLITE_LIST_JSON) as List<SatelliteList>
    }
}
