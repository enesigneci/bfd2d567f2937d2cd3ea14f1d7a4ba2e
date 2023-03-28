package com.enesigneci.satellite.detail.data

import com.enesigneci.satellite.detail.data.datasource.RemotePositionsDataSource
import com.enesigneci.satellite.detail.data.model.PositionList
import com.enesigneci.satellite.detail.domain.PositionRepository
import javax.inject.Inject

class PositionRepositoryImpl @Inject constructor(
    private val positionsDataSource: RemotePositionsDataSource,
) : PositionRepository {
    override suspend fun getPositions(): PositionList {
        return positionsDataSource.getPositions()
    }
}