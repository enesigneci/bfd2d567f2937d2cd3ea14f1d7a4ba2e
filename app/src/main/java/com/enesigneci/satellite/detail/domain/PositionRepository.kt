package com.enesigneci.satellite.detail.domain

import com.enesigneci.satellite.detail.data.model.PositionList

interface PositionRepository {
    suspend fun getPositions(): PositionList
}