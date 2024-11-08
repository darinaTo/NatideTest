package com.example.natifetest.ui.activity

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.natifetest.ui.activity.component.ProgressBar

@Composable
fun GifItem(
    gif: GifUi,
    onItemClick: () -> Unit,
    onDeleteClick: (String) -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }

    val gifEnabledLoader = ImageLoader.Builder(LocalContext.current).components {
        add(ImageDecoderDecoder.Factory())
    }.build()

    Column(
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .height(400.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .clickable { onItemClick() }
        ) {
            AsyncImage(
                model = gif.imageUrl,
                contentDescription = gif.title,
                imageLoader = gifEnabledLoader,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize() ,
                onState = { state ->
                    isLoading = state is AsyncImagePainter.State.Loading
                }
            )
            if (isLoading) {
                ProgressBar()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = gif.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            Button(onClick = { onDeleteClick(gif.id) }) {
                Text(text = "Delete")
            }
        }
    }
}
