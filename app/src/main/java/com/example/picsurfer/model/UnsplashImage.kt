package com.example.picsurfer.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.picsurfer.util.Constants.UNSPLASH_IMAGE_TABLE
import kotlinx.serialization.Serializable

/**This represents a table in the database containing the id of the image
 * the urls property containing details of the image,  the image likes and finally the user/owner of the unsplash image */

/** the @Entity annotation allows you to create a table in the database,
 *  so here UnsplashImage is defined as an entity with its primary key being the id property that
 *  uniquely identifies each row in the table.  */

@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnsplashImage(
    @PrimaryKey(autoGenerate = false ) // false because the value is already unique from the API
    val id: String,
    @Embedded
    val urls: Urls,
    val likes: Int,
    @Embedded
    val user: User,
)
