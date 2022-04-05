package com.enesigneci.satellite.list.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.enesigneci.satellite.detail.data.model.SatelliteDetail
import com.enesigneci.satellite.list.data.db.model.SatelliteList

@Database(entities = [SatelliteList::class, SatelliteDetail::class], version = 1)
abstract class SatelliteDb : RoomDatabase() {
    abstract fun satelliteDao(): SatelliteDao
}
