package com.enesigneci.satellite.detail.data.datasource

import com.enesigneci.satellite.base.Constants
import com.enesigneci.satellite.detail.data.model.PositionList
import com.enesigneci.satellite.di.coroutines.IoDispatcher
import com.enesigneci.satellite.list.data.DataManager
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemotePositionsDataSource @Inject constructor(
    private val dataManager: DataManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getPositions(): PositionList {
        return withContext(ioDispatcher) {
            dataManager.parseJson(Constants.POSITIONS_JSON)
        }
    }
}