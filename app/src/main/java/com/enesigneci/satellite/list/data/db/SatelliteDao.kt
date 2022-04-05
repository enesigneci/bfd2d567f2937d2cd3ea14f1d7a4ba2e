package com.enesigneci.satellite.list.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.list.data.db.model.SatelliteList

@Dao
interface SatelliteDao {
    @Query("SELECT * FROM satellites")
    suspend fun getSatellites(): List<SatelliteList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatelliteList(satellite: SatelliteList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<SatelliteList>)


    @Query("SELECT * FROM satellite_detail WHERE id = :id")
    suspend fun getSatelliteById(id: Int): SatelliteDetail?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSatelliteDetail(satellite: SatelliteDetail)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSatelliteDetails(list: List<SatelliteDetail>)


}
