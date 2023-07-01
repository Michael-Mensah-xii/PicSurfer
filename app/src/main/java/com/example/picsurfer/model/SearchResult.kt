package com.example.picsurfer.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** @SerialName is used because the specified property by
 * the API has been changed to a different name ("results") for this app**/

@Serializable
data class SearchResult(
    @SerialName("results")
    val images: List<UnsplashImage>,
)
