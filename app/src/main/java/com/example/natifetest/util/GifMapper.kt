package com.example.natifetest.util

import com.example.natifetest.domain.entities.dbEntities.GifEntity
import com.example.natifetest.domain.entities.networkEntities.GifData
import com.example.natifetest.domain.entities.networkEntities.GifResponse
import com.example.natifetest.domain.entities.uiEntities.GifUi

object GifMapper {
    fun GifResponse.toDbEntities(): List<GifEntity> {
        return data.map { it.toDbEntity() }
    }

    fun GifEntity.toUIModel(): GifUi {
        return GifUi(
            id = this.id,
            title = this.title,
            imageUrl = this.image
        )
    }

    private fun GifData.toDbEntity(): GifEntity {
        return GifEntity(
            id = this.id,
            title = this.title,
            image = this.images.original.url
        )
    }
}
