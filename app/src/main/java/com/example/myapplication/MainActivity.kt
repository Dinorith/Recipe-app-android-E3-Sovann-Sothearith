package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.repository.MealRepository
import com.example.myapplication.navigation.NavGraph
import com.example.myapplication.ui.navigation.BottomNavigationBar
import com.example.myapplication.viewmodel.MealViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val repository = MealRepository()
            val viewModel = MealViewModel(repository)

            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { padding ->
                NavGraph(navController, viewModel)
            }
        }
    }
}

