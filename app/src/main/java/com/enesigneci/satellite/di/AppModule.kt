package com.enesigneci.satellite.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.enesigneci.satellite.BuildConfig
import com.enesigneci.satellite.base.Provider
import com.enesigneci.satellite.base.StringProvider
import com.enesigneci.satellite.detail.data.PositionRepositoryImpl
import com.enesigneci.satellite.detail.data.datasource.RemotePositionsDataSource
import com.enesigneci.satellite.detail.domain.PositionRepository
import com.enesigneci.satellite.di.coroutines.IoDispatcher
import com.enesigneci.satellite.list.data.datasource.SatelliteDataSource
import com.enesigneci.satellite.list.data.SatelliteRepositoryImpl
import com.enesigneci.satellite.list.data.datasource.AssetDataSource
import com.enesigneci.satellite.list.data.db.SatelliteDb
import com.enesigneci.satellite.list.domain.SatelliteRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSatelliteRepository(
        localDataSource: SatelliteDataSource,
        assetDataSource: AssetDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): SatelliteRepository = SatelliteRepositoryImpl(
        localDataSource,
        assetDataSource,
        ioDispatcher
    )

    @Singleton
    @Provides
    fun providePositionRepository(positionsDataSource: RemotePositionsDataSource): PositionRepository =
        PositionRepositoryImpl(positionsDataSource)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SatelliteDb {
        return Room.databaseBuilder(
            context,
            SatelliteDb::class.java,
            BuildConfig.dbname
        ).build()
    }

    @Singleton
    @Provides
    fun provideSatelliteDatabase(db: SatelliteDb) = db.satelliteDao()

    @Singleton
    @Provides
    fun provideGson() = Gson()


    @Singleton
    @Provides
    fun provideStringProvider(@ApplicationContext context: Context): Provider<String> =
        StringProvider(context)
}
