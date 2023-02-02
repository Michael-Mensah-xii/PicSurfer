package com.example.picsurfer.data.remote

import android.app.appsearch.SearchResult
import com.example.picsurfer.BuildConfig
import com.example.picsurfer.model.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface UnsplashApi {

    //GET request for retrieving images from 1st endpoint(paginate Api response with paging 3 library)
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): List<UnsplashImage>


    //GET request for searching specific image
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): SearchResult


}


