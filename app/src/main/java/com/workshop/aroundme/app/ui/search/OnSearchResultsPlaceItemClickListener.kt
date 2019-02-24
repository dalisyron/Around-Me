package com.workshop.aroundme.app.ui.search

import com.workshop.aroundme.data.model.PlaceEntity

interface OnSearchResultsPlaceItemClickListener {

    fun onPlaceItemClicked(placeEntity: PlaceEntity) {

    }
    fun onItemStarred(placeEntity: PlaceEntity) {

    }
}