package com.example.staggeredphotogallery

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import com.example.staggeredphotogallery.R

fun loadPhotosFromXml(context: Context): List<Photo> {
    val photos = mutableListOf<Photo>()
    val parser = context.resources.getXml(R.xml.photos)

    var title: String? = null
    var fileName: String? = null

    while (parser.eventType != XmlPullParser.END_DOCUMENT) {
        when (parser.eventType) {
            XmlPullParser.START_TAG -> {
                when (parser.name) {
                    "title" -> title = parser.nextText()
                    "file" -> fileName = parser.nextText()
                }
            }
            XmlPullParser.END_TAG -> {
                if (parser.name == "photo" && title != null && fileName != null) {
                    photos.add(Photo(title, fileName))
                    title = null
                    fileName = null
                }
            }
        }
        parser.next()
    }
    return photos
}