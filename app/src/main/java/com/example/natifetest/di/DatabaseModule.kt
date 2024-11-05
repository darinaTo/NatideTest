package com.example.natifetest.di

import android.content.Context
import androidx.room.Room
import com.example.natifetest.data.impl.GifRepositoryImpl
import com.example.natifetest.data.services.local.GifDao
import com.example.natifetest.data.services.local.GifDatabase
import com.example.natifetest.data.services.local.GifLocalDataSource
import com.example.natifetest.data.services.remote.GifRemoteDataSource
import com.example.natifetest.domain.repo.GifRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDao(appDatabase: GifDatabase): GifDao = appDatabase.gifDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): GifDatabase {
        return Room.databaseBuilder(
            appContext,
            GifDatabase::class.java,
            "gif_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideGifRepositoryImpl(
        remoteDataSource: GifRemoteDataSource,
        localDataSource: GifLocalDataSource
    ): GifRepository {
        return GifRepositoryImpl(remoteDataSource, localDataSource)
    }
}