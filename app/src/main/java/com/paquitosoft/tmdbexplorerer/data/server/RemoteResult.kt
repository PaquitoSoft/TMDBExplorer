package com.paquitosoft.tmdbexplorerer.data.server

import com.google.gson.annotations.SerializedName

data class RemoteResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)