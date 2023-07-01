package com.example.picsurfer.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.picsurfer.data.local.dao.UnsplashDatabase
import com.example.picsurfer.data.remote.UnsplashApi
import com.example.picsurfer.model.UnsplashImage
import com.example.picsurfer.model.UnsplashRemoteKeys
import com.example.picsurfer.util.Constants.ITEMS_PER_PAGE
/** The main purpose of the remote mediator is to request new data from the API and cache the response in
 *  the local database hence not showing the data directly from the API but the local database acting as the
 *  single source of truth( data is stored in one central location)
 **/

@ExperimentalPagingApi
class UnsplashRemoteMediator(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase,

    ) : RemoteMediator<Int, UnsplashImage>() {
    //these variables are responsible for saving the images and their remote keys from the API
    private val unsplashImageDao = unsplashDatabase.unsplashImageDao()
    private val unsplashRemoteKeysDao = unsplashDatabase.unsplashRemoteKeysDao()//tell remote mediator which page to load next

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImage>,
    ): MediatorResult {

        return try {
            // get current page value based on load-type
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1 //if the next page value from remote keys table is null set 1 as default page
                }

                //load data at the start of paging data
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                //load data at the end of paging data
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            //make GET request to  photos endpoint
            val response = unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty() //if response is empty no data has been received from the   API so theres no more pages to load

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            unsplashDatabase.withTransaction {
                //check if load-type is refresh or invalidate data
                if (loadType == LoadType.REFRESH) {

                    // and remove records from these database tables
                    unsplashImageDao.deleteAllImages()
                    unsplashRemoteKeysDao.deleteAllRemoteKeys()
                }


                val keys = response.map { unsplashImage ->
                    UnsplashRemoteKeys(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                // after successfully mapping the response to remote keys  save the records  to these database tables
                unsplashRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                unsplashImageDao.addImages(images = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)

        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UnsplashImage>,
    ): UnsplashRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                unsplashRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UnsplashImage>,
    ): UnsplashRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UnsplashImage>,
    ): UnsplashRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                unsplashRemoteKeysDao.getRemoteKeys(id = unsplashImage.id)
            }
    }

}