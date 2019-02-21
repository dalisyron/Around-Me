package com.workshop.aroundme.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.MainActivity.Companion.SEARCH_QUERY_KEY
import com.workshop.aroundme.app.ui.home.HomeFragment
import com.workshop.aroundme.app.ui.home.SearchResultsFragment
import kotlinx.android.synthetic.main.fragment_home.*

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val searchResultsFragment = SearchResultsFragment()
        searchResultsFragment.arguments = intent.extras

        supportFragmentManager.beginTransaction().replace(R.id.content_frame_results, searchResultsFragment).commit()

        setResultsInfo()

    }

    fun setResultsInfo() {
        val resultsInfoTextView = findViewById<TextView>(R.id.resultsInfo)
        val searchQuery = intent.extras?.getString(SEARCH_QUERY_KEY)
        resultsInfoTextView.text = getString(R.string.show_results) + ' ' + searchQuery
    }
}
