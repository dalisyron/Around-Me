package com.workshop.aroundme.data

import android.graphics.BitmapFactory
import com.workshop.aroundme.data.model.ImageSource
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.data.model.Position
import com.workshop.aroundme.remote.datasource.PlaceDataSource
import com.workshop.aroundme.remote.model.response.Image
import java.net.URL

class PlaceRepository(private val placeDataSource: PlaceDataSource) {

    fun getFeaturedPlaces(success: (List<PlaceEntity>?) -> Unit) {
        Thread {
            val result = placeDataSource.getFeaturedPlaces()?.map {

                val images = it.images?.map {
                    val url = URL(it?.image?.tiny?.url)
                    ImageSource(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
                }

                val position : Position? = Position(it.lat, it.lng)

                PlaceEntity(it.full_name, it.address, it.like_count, images, position)
            }
            success(result)
        }.start()
    }
}