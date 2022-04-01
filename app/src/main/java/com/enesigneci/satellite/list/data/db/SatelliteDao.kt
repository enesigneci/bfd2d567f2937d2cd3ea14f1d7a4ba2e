package com.enesigneci.satellite.list.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enesigneci.satellite.list.data.db.model.SatelliteList

@Dao
interface SatelliteDao {
    @Query("SELECT * FROM satellites")
    suspend fun getSatellites(): List<SatelliteList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(satellite: SatelliteList)
}
