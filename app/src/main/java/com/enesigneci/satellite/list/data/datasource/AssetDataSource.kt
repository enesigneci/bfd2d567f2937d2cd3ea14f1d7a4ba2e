package com.enesigneci.satellite.list.data.datasource

import com.enesigneci.satellite.base.Constants
import com.enesigneci.satellite.detail.data.model.PositionList
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.di.coroutines.IoDispatcher
import com.enesigneci.satellite.list.data.DataManager
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
class AssetDataSource @Inject constructor(
    private val dataManager: DataManager
) {
    fun populateSatelliteDatabase(): List<SatelliteList> {
        return dataManager.parseJson(Constants.SATELLITE_LIST_JSON)
    }

    fun populateSatelliteDetailDatabase(): List<SatelliteDetail> {
        return dataManager.parseJson(Constants.SATELLITE_DETAIL_JSON)
    }

    fun getPositions(): PositionList {
        return dataManager.parseJson(Constants.POSITIONS_JSON)
    }
}