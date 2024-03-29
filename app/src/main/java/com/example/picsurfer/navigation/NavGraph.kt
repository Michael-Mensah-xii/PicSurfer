package com.example.picsurfer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.picsurfer.screens.home.HomeScreen
import com.example.picsurfer.screens.search.SearchScreen

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetUpNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route= Screen.Home.route){
            HomeScreen(openSearchScreen = {navController.navigate(Screen.Search.route)})
        }
        composable(route= Screen.Search.route){
           SearchScreen(popBackStack = {navController.popBackStack()})
        }
    }
}