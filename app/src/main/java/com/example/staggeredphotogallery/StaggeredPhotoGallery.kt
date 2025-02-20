package com.example.staggeredphotogallery

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.staggeredphotogallery.ui.PhotoEnlarger


@Composable
fun StaggeredPhotoGallery(context: Context) {
    val photos = remember { loadPhotosFromXml(context) }
    var selectedPhoto by remember { mutableStateOf<Photo?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize(),
            content = {
                items(photos) { photo ->
                    val resId = context.resources.getIdentifier(
                        photo.fileName.substringBeforeLast("."),
                        "drawable",
                        context.packageName
                    )

                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = photo.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clickable { selectedPhoto = photo.copy(resId = resId) }
                    )
                }
            }
        )

        // Show enlarged photo if selected
        PhotoEnlarger(selectedPhoto) { selectedPhoto = null }
    }
}




