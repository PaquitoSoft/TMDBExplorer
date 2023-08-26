package com.paquitosoft.tmdbexplorerer.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.DetailsOverviewRow
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.paquitosoft.tmdbexplorerer.domain.Movie
import com.paquitosoft.tmdbexplorerer.ui.common.loadImageUrl
import java.lang.IllegalStateException

class DetailFragment: DetailsSupportFragment() {

    private val detailsBackgroundState = DetailsBackgroundState(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = requireActivity().intent
            .getParcelableExtra<Movie>(DetailActivity.MOVIE_EXTRA)
            ?: throw IllegalStateException("Movie not found")

        val presenter = FullWidthDetailsOverviewRowPresenter(
            DetailsDescriptionPresenter()
        )

        val rowsAdapter = ArrayObjectAdapter(presenter)
        val detailsOverviewRow = DetailsOverviewRow(movie)
        detailsOverviewRow.loadImageUrl(requireContext(), movie.poster)
//        Glide.with(requireContext())
//            .load(movie.poster)
//            .centerCrop()
//            .into(object : CustomTarget<Drawable>() {
//                override fun onResourceReady(
//                    resource: Drawable,
//                    transition: Transition<in Drawable>?
//                ) {
//                    detailsOverviewRow.imageDrawable = resource
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {}
//
//            })

        rowsAdapter.add(detailsOverviewRow)

        adapter = rowsAdapter
        detailsBackgroundState.loadUrl(movie.backdrop)
    }
}

class DetailsDescriptionPresenter: AbstractDetailsDescriptionPresenter() {
    override fun onBindDescription(viewHolder: ViewHolder, item: Any) {
        val movie = item as Movie

        viewHolder.title.text = movie.title
        viewHolder.subtitle.text = movie.releaseDate
        viewHolder.body.text = movie.summary
    }
}