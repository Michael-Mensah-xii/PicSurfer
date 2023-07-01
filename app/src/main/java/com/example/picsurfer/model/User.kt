package com.example.picsurfer.model

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**The user data class
 * Note: the @Embedded annotation is used to indicate that a property
 * in an entity class should be embedded in the table for that entity,
 * rather than being stored as a separate table. so in this case userLinks and username should be stored within the User **/

@Serializable
data class User(
    /** SerializedName reflects a change in the property name allowing you
    to specify the exact property from Unsplash Api   **/

    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String,
)
