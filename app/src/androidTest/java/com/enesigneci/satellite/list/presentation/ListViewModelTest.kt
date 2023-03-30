package com.enesigneci.satellite.list.presentation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.enesigneci.satellite.list.data.db.SatelliteDao
import com.enesigneci.satellite.list.data.db.SatelliteDb
import com.enesigneci.satellite.list.data.db.model.SatelliteList
import com.google.common.truth.Truth
import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewModelTest {
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    private lateinit var satelliteDao: SatelliteDao
    private lateinit var db: SatelliteDb

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, SatelliteDb::class.java).build()
        satelliteDao = db.satelliteDao()
        runTest {
            satelliteDao.insertSatelliteList(
                SatelliteList(
                    true, 1, "test"
                )
            )
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadSatellites() = runTest {
        val satellites = satelliteDao.getSatellites()
        Truth.assertThat(satellites).hasSize(1)
    }
}