package com.example.natifetest.data.services.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.natifetest.domain.entities.dbEntities.GifEntity

@Database(entities = [GifEntity::class], version = 1)
abstract class GifDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao

}