package com.paquitosoft.tmdbexplorerer.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.app.DetailsSupportFragmentBackgroundController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class DetailsBackgroundState(private val fragment: DetailsSupportFragment) {
//    private val detailsBackground = DetailsSupportFragmentBackgroundController(fragment)

    // Lazy instantiation
    // The instance is not created until this class is instantiated for the first time
    private val detailsBackground by lazy { DetailsSupportFragmentBackgroundController(fragment) }

    private val target = object : CustomTarget<Bitmap>() {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            detailsBackground.coverBitmap = resource
            detailsBackground.enableParallax()
        }

        override fun onLoadCleared(placeholder: Drawable?) { }
    }

    fun loadUrl(url: String) {
        Glide.with(fragment.requireContext())
            .asBitmap()
            .centerCrop()
            .load(url)
            .into(target)
    }
}