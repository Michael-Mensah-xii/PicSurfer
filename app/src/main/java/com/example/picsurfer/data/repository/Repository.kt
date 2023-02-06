package com.example.picsurfer.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.picsurfer.data.local.dao.UnsplashDatabase
import com.example.picsurfer.data.paging.SearchPagingSource
import com.example.picsurfer.data.paging.UnsplashRemoteMediator
import com.example.picsurfer.data.remote.UnsplashApi
import com.example.picsurfer.model.UnsplashImage
import com.example.picsurfer.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalPagingApi
class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase,
) {

    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(unsplashApi, unsplashDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = unsplashApi, query = query)
            }
        ).flow

    }
}


/*
@ExperimentalPagingApi
class Repository @Inject constructor(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) {

    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UnsplashRemoteMediator(unsplashApi, unsplashDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}*/


/**
@ExperimentalPagingApi
class Repository @Inject constructor(
private val unsplashApi: UnsplashApi,
private val unsplashDatabase: UnsplashDatabase
) {
fun getAllImages(): Flow<PagingData<UnsplashImage>> {
val pagingSource = unsplashDatabase.unsplashImageDao().getAllImages()
return Pager(
config = PagingConfig(pageSize = ITEMS_PER_PAGE),
remoteMediator = UnsplashRemoteMediator(
unsplashApi = unsplashApi,
unsplashDatabase = unsplashDatabase
),
pagingSourceFactory = { pagingSource }
).flow
}
}**/


/* fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
     return Pager(
         config = PagingConfig(pageSize = ITEMS_PER_PAGE),
         pagingSourceFactory = {
             SearchPagingSource(unsplashApi = unsplashApi, query = query)
         }
     ).flow
 }*/

