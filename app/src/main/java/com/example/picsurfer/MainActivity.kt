 package com.example.picsurfer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.picsurfer.navigation.SetUpNavGraph
import com.example.picsurfer.ui.theme.PicSurferTheme
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    @ExperimentalPagingApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicSurferTheme {
                val navController = rememberNavController()
                 SetUpNavGraph(navController = navController)

            }
        }
    }
}

