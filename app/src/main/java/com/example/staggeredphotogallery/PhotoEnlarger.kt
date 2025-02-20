package com.example.staggeredphotogallery.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.staggeredphotogallery.Photo

@Composable
fun PhotoEnlarger(photo: Photo?, onClose: () -> Unit) {
    if (photo == null) return

    // Increase the scale factor for a bigger enlargement
    val scale by animateFloatAsState(
        targetValue = 2f, // Make it twice as large
        animationSpec = tween(durationMillis = 300),
        label = "Photo Scale Animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f)) // Dimmed background
            .clickable { onClose() }, // Close when clicked
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = photo.resId),
            contentDescription = photo.title,
            modifier = Modifier
                .fillMaxWidth()  // Image takes up full width of the screen
                .graphicsLayer(scaleX = scale, scaleY = scale)
                .clickable { onClose() } // Tap again to close
        )
    }
}

