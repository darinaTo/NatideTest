package com.example.natifetest.data.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.natifetest.domain.entities.dbEntities.GifEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertsGif(gifs: List<GifEntity>)

    @Query("SELECT * FROM gifs WHERE id =:id")
     fun getGifById(id : String) : Flow<GifEntity>
    @Query("SELECT * FROM gifs")
     fun getGifs(): Flow<List<GifEntity>>
}
