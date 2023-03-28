package com.enesigneci.satellite.detail.domain

import com.enesigneci.satellite.detail.data.model.Positions
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.list.domain.SatelliteRepository
import javax.inject.Inject
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.isActive

class DetailUseCase @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    private val positionRepository: PositionRepository
) {
    private suspend fun getSatelliteById(id: Int): Flow<SatelliteDetail?> {
        return flowOf(satelliteRepository.getSatelliteById(id))
    }

    private suspend fun getPositions(id: Int): Positions? {
        return positionRepository.getPositions().list.find { it.id == id.toString() }
    }

    suspend fun prepareCombinedFlow(id: Int): Flow<Pair<SatelliteDetail?, Positions?>> {
        val timer = flow {
            while (currentCoroutineContext().isActive) {
                emit(getPositions(id))
                delay(3000)
            }
        }
        return combine(
            getSatelliteById(id),
            timer
        ) { satellite, positions ->
            satellite to positions
        }
    }
}
