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

    /* set up a remoteMediator using the UnsplashRemoteMediator class to load
      additional pages from the Unsplash API when needed*/

/**The getAllImages function uses the remote mediator to fetch the data from the unsplashApi
 * and store the result of the request into the unsplashDatabase*/

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
