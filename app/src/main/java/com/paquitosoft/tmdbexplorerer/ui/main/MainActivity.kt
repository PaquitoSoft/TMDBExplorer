package com.paquitosoft.tmdbexplorerer.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.paquitosoft.tmdbexplorerer.R

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}