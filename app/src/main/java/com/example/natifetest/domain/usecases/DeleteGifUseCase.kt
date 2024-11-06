package com.example.natifetest.domain.usecases

import com.example.natifetest.domain.repo.GifRepository
import javax.inject.Inject

class DeleteGifUseCase @Inject constructor(
    private val gifRepository: GifRepository
) {
    suspend operator fun invoke(gifId: String) {
        gifRepository.deleteGif(gifId)
    }
}
