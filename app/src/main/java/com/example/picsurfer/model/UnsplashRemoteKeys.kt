package com.example.picsurfer.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.picsurfer.util.Constants.UNSPLASH_REMOTE_KEYS_TABLE

/** This table is used by the remote mediator to know which page to
 * request next from the API it takes the id variable as its primary key
 * with the previous and next page variables having nullable integer type
 */

@Entity(tableName = UNSPLASH_REMOTE_KEYS_TABLE)
data class UnsplashRemoteKeys(
    @PrimaryKey(autoGenerate = false )
    val id: String,
    val prevPage: Int?,
    val nextPage: Int? ,
)