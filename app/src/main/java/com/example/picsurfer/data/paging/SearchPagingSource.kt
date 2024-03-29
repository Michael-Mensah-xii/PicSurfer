package com.example.picsurfer.data.paging

import com.example.picsurfer.data.remote.UnsplashApi
import com.example.picsurfer.model.UnsplashImage
import com.example.picsurfer.util.Constants.ITEMS_PER_PAGE
import androidx.paging.PagingSource
import androidx.paging.PagingState

/**Note: in the pagingSource the key decides what to load and the value is the data loaded*/

// loading and refreshing pages of data from the Unsplash API and providing it to the "Paging" library.
class SearchPagingSource(
    private val unsplashApi: UnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        //get current page value
        val currentPage = params.key ?: 1
        return try {
            //send get request to searchImages endpoint in unsplash API
            val response = unsplashApi.searchImages(query = query, perPage = ITEMS_PER_PAGE)

            //check if image list is empty
            val endOfPaginationReached = response.images.isEmpty()

            if (response.images.isNotEmpty()) {
                LoadResult.Page(
                    data = response.images,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }
}
