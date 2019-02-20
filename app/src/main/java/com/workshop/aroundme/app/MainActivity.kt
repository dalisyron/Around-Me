package com.workshop.aroundme.app

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_QUERY_KEY = "searchQuery"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, HomeFragment())
            .commit()

        val searchBtn = findViewById<ImageButton>(R.id.searchImageButton)
        val searchEditText = findViewById<EditText>(R.id.searchQueryEditText)

        searchBtn.setOnClickListener {
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra(SEARCH_QUERY_KEY, searchEditText.text.toString())
            startActivity(intent)
        }
    }
}
