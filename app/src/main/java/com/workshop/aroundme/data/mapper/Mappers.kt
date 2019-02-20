package com.workshop.aroundme.data.mapper

import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.model.response.PlaceDto

fun PlaceDto.toPlaceEntity() = PlaceEntity(
    name = full_name,
    address = address,
    likes = like_count,
    imageUrl = images?.getOrNull(0)?.image?.card?.url,
    isFavorite = is_liked != null && is_liked
)

