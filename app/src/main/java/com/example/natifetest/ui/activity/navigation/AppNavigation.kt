package com.example.natifetest.ui.activity.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.natifetest.ui.activity.GifDetailScreen
import com.example.natifetest.ui.activity.GifScreen
import com.example.natifetest.util.Constants.GIF_DETAIL_SCREEN
import com.example.natifetest.util.Constants.GIF_SCREEN

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = GIF_SCREEN) {
        val navigateToDetail: (Int) -> Unit = { index ->
            navController.navigate("${GIF_DETAIL_SCREEN}/$index")
        }
        composable(GIF_SCREEN) {
            GifScreen(onItemClick = navigateToDetail)
        }
        composable("$GIF_DETAIL_SCREEN/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })) {
            GifDetailScreen()
        }
    }
}