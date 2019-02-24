package com.workshop.aroundme.app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.home.HomeFragment
import com.workshop.aroundme.app.ui.login.LoginFragment
import com.workshop.aroundme.app.ui.search.SearchResultsFragment
import com.workshop.aroundme.app.ui.starred.StarredFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_QUERY_KEY = "searchQuery"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val userRepository = Injector.provideUserRepository(this)
        if (userRepository.isLoggedIn()) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, HomeFragment())
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, LoginFragment())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.starredPlaces -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, StarredFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
