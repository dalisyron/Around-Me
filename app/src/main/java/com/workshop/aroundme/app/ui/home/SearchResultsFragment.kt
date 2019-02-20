package com.workshop.aroundme.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.workshop.aroundme.R
import com.workshop.aroundme.app.MainActivity.Companion.SEARCH_QUERY_KEY
import com.workshop.aroundme.data.PlaceRepository
import com.workshop.aroundme.data.model.PlaceEntity
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.datasource.PlaceDataSource
import com.workshop.aroundme.remote.service.PlaceService

class SearchResultsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val placeRepository = PlaceRepository(PlaceDataSource(PlaceService(NetworkManager())))

        val searchQuery = arguments?.getString(SEARCH_QUERY_KEY)?:""

        placeRepository.getSearchResultPlaces(searchQuery, ::onSearchResultsReady)
    }

    private fun onSearchResultsReady(list: List<PlaceEntity>?) {
        activity?.runOnUiThread {
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar)
            progressBar?.visibility = View.GONE
            recyclerView?.adapter = HomeAdapter(list ?: listOf())
        }
    }
}
