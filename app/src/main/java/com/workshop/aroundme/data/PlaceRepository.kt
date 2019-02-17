package com.workshop.aroundme.data

import com.workshop.aroundme.data.model.ImageSource
import com.workshop.aroundme.data.model.ImageSourceX
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.model.Position
import com.workshop.aroundme.remote.datasource.PlaceDataSource
import com.workshop.aroundme.remote.model.response.Image

class PlaceRepository(private val placeDataSource: PlaceDataSource) {

    fun getFeaturedPlaces(success: (List<PlaceEntity>?) -> Unit) {
        Thread {
            val result = placeDataSource.getFeaturedPlaces()?.map {

                val images = it.images?.map {
                    ImageSource(ImageSourceX(it?.image?.card?.url, it?.image?.large?.url, it?.image?.medium?.url, it?.image?.small?.url, it?.image?.tiny?.url))
                }

                val position : Position? = Position(it.lat, it.lng)

                PlaceEntity(it.full_name, it.address, it.like_count, images, position)
            }
            success(result)
        }.start()
    }
}