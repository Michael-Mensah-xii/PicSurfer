package com.example.picsurfer.screens.home

import com.example.picsurfer.data.repository.Repository
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: Repository
): ViewModel() {
    val getAllImages = repository.getAllImages()
}