package com.paquitosoft.tmdbexplorerer.ui.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.leanback.widget.DetailsOverviewRow
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

// Extending framework class with a new function
fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}
fun DetailsOverviewRow.loadImageUrl(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into(object : CustomTarget<Drawable>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                imageDrawable = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}

        })
}