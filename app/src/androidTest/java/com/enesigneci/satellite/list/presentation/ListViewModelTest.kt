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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListViewModelTest {
    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    @ExperimentalCoroutinesApi
    val testScope: TestCoroutineScope = TestCoroutineScope(testDispatcher)
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }
    private lateinit var satelliteDao: SatelliteDao
    private lateinit var db: SatelliteDb

    @ExperimentalCoroutinesApi
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, SatelliteDb::class.java).build()
        satelliteDao = db.satelliteDao()
        testScope.runBlockingTest {
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
    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadSatellites() = testScope.runBlockingTest {
        val satellites = satelliteDao.getSatellites()
        Truth.assertThat(satellites).hasSize(1)
    }
}