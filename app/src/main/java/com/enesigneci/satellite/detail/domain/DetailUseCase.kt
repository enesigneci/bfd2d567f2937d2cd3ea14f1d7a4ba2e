package com.enesigneci.satellite.detail.domain

import com.enesigneci.satellite.detail.data.model.PositionList
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.list.domain.SatelliteRepository
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend fun getSatelliteById(id: Int, name: String): SatelliteDetail = satelliteRepository.getSatelliteById(id, name)
    suspend fun getPositions(): PositionList = satelliteRepository.getPositions()
}
