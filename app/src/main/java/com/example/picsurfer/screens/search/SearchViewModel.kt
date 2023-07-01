package com.example.picsurfer.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.picsurfer.data.repository.Repository
import com.example.picsurfer.model.UnsplashImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    private val _searchedImages = MutableStateFlow<PagingData<UnsplashImage>>(PagingData.empty())
    val searchedImages = _searchedImages.asStateFlow()

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun searchStuff(query: String) {
        viewModelScope.launch {
            repository.searchImages(query = query).cachedIn(viewModelScope).collect {
                _searchedImages.value = it
            }
        }
    }

}

