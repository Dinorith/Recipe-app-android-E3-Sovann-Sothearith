package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.onboarding.OnboardingScreen
import com.example.myapplication.ui.home.HomeScreen
import com.example.myapplication.ui.explore.ExploreScreen
import com.example.myapplication.ui.mealdetail.MealDetailScreen
import com.example.myapplication.ui.favorite.FavoriteScreen
import com.example.myapplication.viewmodel.MealViewModel

@Composable
fun NavGraph(navController: NavHostController, viewModel: MealViewModel) {
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(navController)
        }
        composable("home") {
            HomeScreen(viewModel, navController)
        }
        composable("explore") {
            ExploreScreen(viewModel, navController)
        }
        composable("mealDetail/{mealId}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
            MealDetailScreen(mealId, viewModel, navController)
        }
        composable("favorite") {
            FavoriteScreen(viewModel, navController)
        }
    }
}
