package com.paquitosoft.tmdbexplorerer.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.lifecycle.lifecycleScope
import com.paquitosoft.tmdbexplorerer.R
import com.paquitosoft.tmdbexplorerer.data.MoviesRepository
import com.paquitosoft.tmdbexplorerer.data.server.RemoteConnection
import com.paquitosoft.tmdbexplorerer.data.server.RemoteService
import com.paquitosoft.tmdbexplorerer.data.server.toDomainMovie
import com.paquitosoft.tmdbexplorerer.domain.Movie
import com.paquitosoft.tmdbexplorerer.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainFragment: BrowseSupportFragment() {

    private lateinit var moviesRepository: MoviesRepository

    private suspend fun buildAdapter(): ArrayObjectAdapter {
        // Adapter dictates how elements are rendered in the screen
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()
        moviesRepository.getCategories().forEach { (category, movies) ->
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            val header = HeaderItem(category.ordinal.toLong(), category.name)

            // Populate the adapter for this row with the category movies
            listRowAdapter.addAll(0, movies)

            // Every row we add to the parent adapter has a title
            // and the adapter for the list of its own items
            rowsAdapter.add(ListRow(header, listRowAdapter))
        }

        return rowsAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesRepository = MoviesRepository(getString(R.string.api_key))

        title = getString(R.string.browse)

        // Adapter dictates how elements are rendered in the screen
//        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // This is to create a coroutine context where we
        // manage network waits out of the main thread of the application
        viewLifecycleOwner.lifecycleScope.launch {
//            val movies = RemoteConnection.service
//                .listPopularMovies(getString(R.string.api_key))
//                .results.map { it.toDomainMovie() }

            // Now we need to add the rows to the adapter
            // Every row will have a title an an array of the items (columns)
            // to render in it
//            (1..5).forEach { categoryId ->
//                val categoryTitle = "Category $categoryId"
//
//                // This is the adapter for this row
//                val listRowAdapter = ArrayObjectAdapter(CardPresenter())
////                listRowAdapter.addAll(0, (1..10).map {
////                    Movie(
////                        "Title $it",
////                        2021,
////                        "https://loremflickr.com/176/313/car?lock=$it"
////                    )
////                })
//                listRowAdapter.addAll(0, movies)
//
//                val header = HeaderItem(categoryId.toLong(), categoryTitle)
//
//                // Every row we add to the parent adapter has a title
//                // and the adapter for the list of its own items
//                rowsAdapter.add(ListRow(header, listRowAdapter))
//            }

//            adapter = rowsAdapter
            adapter = buildAdapter()
        }

        onItemViewClickedListener = OnItemViewClickedListener { _, movie, _, _ ->
            // Here we're saying we want to navigate the to detail activity
            // and pass the clicked movie as part of the context to the new activity
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra(DetailActivity.MOVIE_EXTRA, movie as Movie)
            }
            startActivity(intent)
        }
    }
}

