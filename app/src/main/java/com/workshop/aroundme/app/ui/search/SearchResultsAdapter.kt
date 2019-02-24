package com.workshop.aroundme.app.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.HomeViewHolder
import com.workshop.aroundme.data.model.PlaceEntity

class SearchResultsAdapter(
    val items : List<PlaceEntity>,
    val onSearchResultsPlaceItemClickListener: OnSearchResultsPlaceItemClickListener
)
    : RecyclerView.Adapter<SearchResultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_results_place_item, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(items.get(position), onSearchResultsPlaceItemClickListener)
    }

}