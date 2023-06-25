package com.example.picsurfer.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.picsurfer.screens.common.ListContent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun SearchScreen(
    popBackStack: () -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val searchedImages = searchViewModel.searchedImages.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            SearchWidget(
                text = searchViewModel.searchQuery,
                onTextChange = {
                    // searchQuery = it
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchStuff(query = it)
                },
                onCloseClicked = {
                    popBackStack()
                }
            )
        },
        content = {
            ListContent(items = searchedImages)
        }
    )
}