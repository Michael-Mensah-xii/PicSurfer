package com.example.picsurfer.model

import kotlinx.serialization.Serializable

/** this data class contains an html property of type string **/

@Serializable
data class UserLinks(
    val html: String
)
