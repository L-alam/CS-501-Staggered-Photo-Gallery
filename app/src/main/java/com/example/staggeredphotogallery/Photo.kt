package com.example.staggeredphotogallery

data class Photo(
    val title: String,
    val fileName: String,
    val resId: Int = 0 // Default to 0, will be assigned later
)