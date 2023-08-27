package com.paquitosoft.tmdbexplorerer.ui.detail

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.leanback.widget.AbstractDetailsDescriptionPresenter
import androidx.leanback.widget.Action
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter
import androidx.leanback.widget.FullWidthDetailsOverviewSharedElementHelper
import com.paquitosoft.tmdbexplorerer.R
import com.paquitosoft.tmdbexplorerer.domain.Movie

class FullWidthDetailsMovieRowPresenter(private val activity: Activity):
    FullWidthDetailsOverviewRowPresenter(DetailsDescriptionPresenter()) {

    private enum class Options(@StringRes val stringRes: Int) {
        WATCH_TRAILER(R.string.watch_trailer),
        FAVORITE(R.string.add_to_favorites)
    }

    init {
        val sharedElementHelper = FullWidthDetailsOverviewSharedElementHelper()
        sharedElementHelper.setSharedElementEnterTransition(
            activity,
            DetailActivity.SHARED_ELEMENT_NAME
        )
        setListener(sharedElementHelper)
        isParticipatingEntranceTransition = true

    }

    fun buildActions(): ArrayObjectAdapter {

        setOnActionClickedListener { action ->
            val option = Options.values().first {
                @Suppress("DEPRECATED_IDENTITY_EQUALS")
                it.ordinal === action.id.toInt()
            }
            Toast.makeText(activity, option.stringRes, Toast.LENGTH_SHORT).show()
        }

        return ArrayObjectAdapter().apply {
            Options.values().forEach { option ->
                add(Action(option.ordinal.toLong(), activity.getString(option.stringRes)))
            }
        }
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