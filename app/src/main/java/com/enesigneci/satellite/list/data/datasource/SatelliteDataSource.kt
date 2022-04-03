package com.enesigneci.satellite.list.data.datasource

import com.enesigneci.satellite.base.Constants
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
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

    suspend fun insertSatelliteList(satelliteList: SatelliteList) = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().insertSatelliteList(satelliteList)
    }

    private suspend fun insertAllSatellites(list: List<SatelliteList>) = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().insertAll(list)
    }

    suspend fun getSatelliteById(id: Int) = withContext(ioDispatcher) {
        prepareSatelliteDetailData(id)
    }

    private suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetail) = withContext(ioDispatcher) {
        satelliteDb.satelliteDao().insertSatelliteDetail(satelliteDetail)
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

    private suspend fun prepareSatelliteDetailData(id: Int): SatelliteDetail {
        val satelliteFromDb = satelliteDb.satelliteDao().getSatelliteById(id)
        return if (satelliteFromDb == null) {
            val satelliteDetail = populateSatelliteDetailDatabase().find { it.id == id }!!
            insertSatelliteDetail(satelliteDetail)
            satelliteDetail
        } else {
            satelliteFromDb
        }
    }

    private fun populateSatelliteDetailDatabase(): List<SatelliteDetail> {
        return dataManager.parseJson(Constants.SATELLITE_DETAIL_JSON) as List<SatelliteDetail>
    }
}
