package com.example.picsurfer.model.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.picsurfer.screens.home.HomeScreen

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetUpNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route= Screen.Home.route){
            HomeScreen(navController=navController)
        }
        composable(route= Screen.Search.route){
      //      SearchScreen(navController=navController)
        }
    }
}