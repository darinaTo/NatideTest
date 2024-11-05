package com.example.natifetest.ui.activity.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.natifetest.ui.activity.GifScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "gif_screen_route") {
        composable("gif_screen_route") {
            GifScreen()
        }
    }
}