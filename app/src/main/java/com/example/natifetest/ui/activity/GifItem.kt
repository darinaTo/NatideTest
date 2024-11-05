package com.example.natifetest.ui.activity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.decode.ImageDecoderDecoder
import com.example.natifetest.domain.entities.uiEntities.GifUi

@Composable
fun GifItem(
    gif: GifUi,
    onItemClick: (String) -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }

    val gifEnabledLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(ImageDecoderDecoder.Factory())
        }.build()
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(400.dp)
            .fillMaxWidth()
            .clickable { onItemClick(gif.id) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = gif.imageUrl,
                contentDescription = gif.title,
                imageLoader = gifEnabledLoader,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                onState = { state ->
                    isLoading = when (state) {
                        is AsyncImagePainter.State.Loading -> true
                        else -> false
                    }
                }
            )
            Text(
                text = gif.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

