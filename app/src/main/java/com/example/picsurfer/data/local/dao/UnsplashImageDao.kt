package com.example.picsurfer.data.local.dao

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.picsurfer.model.UnsplashImage
/** The Dao(Data Access Object) provides an abstract interface to the
 *  database and exposes methods to interact with them as shown below */

@Dao
interface UnsplashImageDao {
    @Query("SELECT * FROM unsplash_image_table")
    fun getAllImages(): PagingSource<Int, UnsplashImage>// returns a paging source(to allow pagination in database also) with parameters (int representing page number and the image)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addImages(images: List<UnsplashImage>) // adds a list of images into the database

    @Query("DELETE FROM unsplash_image_table")
    suspend fun deleteAllImages() // deletes all images from the database table
}
