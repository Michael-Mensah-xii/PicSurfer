package com.example.picsurfer.data.remote

import com.example.picsurfer.model.SearchResult
import com.example.picsurfer.BuildConfig
import com.example.picsurfer.model.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    //GET request for retrieving images from 1st endpoint(paginate Api response with paging 3 library)
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")// /photos is the endpoint for listing all images from the API
    suspend fun getAllImages(
        @Query("page") page: Int, //PAGE number to retrieve from the API
        @Query("per_page") perPage: Int, //The number of items per page
    ): List<UnsplashImage> // finally return a list containing the Unsplash images


    //GET request for searching specific image
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos") // /search/photos is the endpoint for listing all images from the API /

    suspend fun searchImages(
        @Query("query") query: String, // string query containing the searched
        @Query("per_page") perPage: Int, // The number of items per page
    ): SearchResult // finally return a list containing the image search results


}


