package com.workshop.aroundme.data.model

import android.graphics.Bitmap

data class PlaceEntity(
    val name: String?,
    val address : String?,
    val likeCount : Int?,
    val images : List<ImageSource?>?,
    val position : Position?
)

data class Position(val latitude : String?, val longitude : String?);

data class ImageSource(
    val bitmap: Bitmap?
)