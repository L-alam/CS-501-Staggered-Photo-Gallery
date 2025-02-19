package com.example.staggeredphotogallery

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun StaggeredPhotoGallery(context: Context) {
    val photos = remember { loadPhotosFromXml(context) }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
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
                        .wrapContentHeight()
                        .clickable { /* TODO: Add animation */ }
                )
            }
        }
    )
}
