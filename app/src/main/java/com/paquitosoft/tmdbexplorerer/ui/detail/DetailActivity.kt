package com.paquitosoft.tmdbexplorerer.ui.detail

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.paquitosoft.tmdbexplorerer.R

class DetailActivity : FragmentActivity() {

    companion object {
        const val MOVIE_EXTRA = "extra:movie"
        const val SHARED_ELEMENT_NAME = "extra:hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}