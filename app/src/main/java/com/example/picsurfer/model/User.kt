package com.example.picsurfer.model

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    //SerializedName to get exact property from Unsplash Api while edited
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String,
)
