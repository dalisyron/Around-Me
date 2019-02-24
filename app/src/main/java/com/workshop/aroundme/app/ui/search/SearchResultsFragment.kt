package com.workshop.aroundme.app.ui.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.MainActivity.Companion.SEARCH_QUERY_KEY
import com.workshop.aroundme.app.ui.detail.DetailFragment
import com.workshop.aroundme.app.ui.home.HomeAdapter
import com.workshop.aroundme.app.ui.home.OnHomePlaceItemClickListener
import com.workshop.aroundme.data.model.PlaceEntity

class SearchResultsFragment : Fragment(), OnSearchResultsPlaceItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val placeRepository = Injector.providePlaceRepository(requireContext())

        val searchQuery = arguments?.getString(SEARCH_QUERY_KEY) ?: "No Search Query"

        placeRepository.getSearchResultPlaces(searchQuery, ::onSearchResultsReady)
    }

    private fun onSearchResultsReady(list: List<PlaceEntity>?) {
        activity?.runOnUiThread {
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            val progressBar = view?.findViewById<ProgressBar>(R.id.loadingBar)
            progressBar?.visibility = View.GONE
            recyclerView?.adapter = SearchResultsAdapter(list ?: listOf(), this)
        }
    }

    override fun onPlaceItemClicked(placeEntity: PlaceEntity) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame, DetailFragment.newInstance(placeEntity.slug))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onItemStarred(placeEntity: PlaceEntity) {
        val placeRepository = Injector.providePlaceRepository(requireContext())
        placeRepository.starPlace(placeEntity)
    }

    companion object {
        fun newInstance(searchQuery: String): SearchResultsFragment {
            val instance = SearchResultsFragment()
            instance.apply {
                this.arguments = Bundle().apply { putString(SEARCH_QUERY_KEY, searchQuery) }
            }
            return instance
        }
    }
}
