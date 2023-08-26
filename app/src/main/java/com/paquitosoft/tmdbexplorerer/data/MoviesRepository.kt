package com.paquitosoft.tmdbexplorerer.data

import com.paquitosoft.tmdbexplorerer.data.server.RemoteConnection
import com.paquitosoft.tmdbexplorerer.data.server.toDomainMovie
import com.paquitosoft.tmdbexplorerer.domain.Category
import com.paquitosoft.tmdbexplorerer.domain.Movie

class MoviesRepository(private val apiKey: String) {

    suspend fun getCategories(): Map<Category, List<Movie>> {
        return Category.values().associateWith { category ->
            RemoteConnection.service
                .listMovies(apiKey, category.id)
                .results.map { it.toDomainMovie() }
        }
    }

}