package com.enesigneci.satellite.detail.domain

import com.enesigneci.satellite.detail.data.model.Positions
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.list.domain.SatelliteRepository
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend fun getSatelliteById(id: Int): SatelliteDetail = satelliteRepository.getSatelliteById(id)
    suspend fun getPositions(id: String): Positions? = satelliteRepository.getPositions().list.find { it.id == id }
}
