package com.workshop.aroundme.data.model

data class PlaceEntity(
    val name: String?,
    val address : String?,
    val likeCount : Int?,
    val images : List<ImageSource?>?,
    val position : Position?
)

data class Position(val latitude : String?, val longitude : String?);

data class ImageSource(
    val image: ImageSourceX?
)

data class ImageSourceX(
    val card: String?,
    val large: String?,
    val medium: String?,
    val small: String?,
    val tiny: String?
)
