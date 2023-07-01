package com.example.picsurfer.model

import kotlinx.serialization.Serializable

/** the regular property is used to ensure an image with a regular size **/
@Serializable
data class Urls(
    val regular: String,
)
