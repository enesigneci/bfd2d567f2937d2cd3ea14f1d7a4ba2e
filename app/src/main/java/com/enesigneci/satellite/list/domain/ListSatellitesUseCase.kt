package com.enesigneci.satellite.list.domain

import com.enesigneci.satellite.list.data.db.model.SatelliteList
import javax.inject.Inject

class ListSatellitesUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) {
    suspend fun loadSatellites(): List<SatelliteList> = satelliteRepository.loadSatellites()
}
