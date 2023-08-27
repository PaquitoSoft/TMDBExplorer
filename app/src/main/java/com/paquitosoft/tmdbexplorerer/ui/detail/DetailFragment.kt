package com.paquitosoft.tmdbexplorerer.ui.detail

import android.os.Bundle
import android.view.View
import androidx.leanback.app.DetailsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.DetailsOverviewRow
import com.paquitosoft.tmdbexplorerer.domain.Movie
import com.paquitosoft.tmdbexplorerer.ui.common.loadImageUrl

class DetailFragment: DetailsSupportFragment() {

    private val detailsBackgroundState = DetailsBackgroundState(this)

//    private enum class Options(@StringRes val stringRes: Int) {
//        WATCH_TRAILER(R.string.watch_trailer),
//        FAVORITE(R.string.add_to_favorites)
//    }

//    private fun buildActions(presenter: FullWidthDetailsOverviewRowPresenter): ArrayObjectAdapter {
//
//        presenter.setOnActionClickedListener { action ->
//            val option = Options.values().first { it.ordinal === action.id.toInt() }
//            Toast.makeText(requireContext(), option.stringRes, Toast.LENGTH_SHORT).show()
//        }
//
//        return ArrayObjectAdapter().apply {
//            Options.values().forEach { option ->
//                add(Action(option.ordinal.toLong(), getString(option.stringRes)))
//            }
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = requireActivity().intent
            .getParcelableExtra<Movie>(DetailActivity.MOVIE_EXTRA)
            ?: throw IllegalStateException("Movie not found")

        // This is the one which handles events on the actions we pass
        // below to the detailsRowOverview
//        val presenter = FullWidthDetailsOverviewRowPresenter(
//            DetailsDescriptionPresenter()
//        )
        val presenter = FullWidthDetailsMovieRowPresenter(requireActivity())

        // This code block is required for the transition animation from the main view
        // the details one to work
        // We're specifying parameters to indicate we are the target of a transition
//        val sharedElementHelper = FullWidthDetailsOverviewSharedElementHelper()
//        sharedElementHelper.setSharedElementEnterTransition(
//            requireActivity(),
//            DetailActivity.SHARED_ELEMENT_NAME
//        )
//        presenter.setListener(sharedElementHelper)
//        presenter.isParticipatingEntranceTransition = true

        val rowsAdapter = ArrayObjectAdapter(presenter)
        val detailsOverviewRow = DetailsOverviewRow(movie)
        detailsOverviewRow.loadImageUrl(requireContext(), movie.poster)
//        detailsOverviewRow.actionsAdapter = buildActions(presenter)
        detailsOverviewRow.actionsAdapter = presenter.buildActions()

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

//class DetailsDescriptionPresenter: AbstractDetailsDescriptionPresenter() {
//    override fun onBindDescription(viewHolder: ViewHolder, item: Any) {
//        val movie = item as Movie
//
//        viewHolder.title.text = movie.title
//        viewHolder.subtitle.text = movie.releaseDate
//        viewHolder.body.text = movie.summary
//    }
//}