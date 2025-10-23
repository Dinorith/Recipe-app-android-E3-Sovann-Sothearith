package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.data.model.Meal
import com.example.myapplication.viewmodel.MealViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealListScreen(mealViewModel: MealViewModel = viewModel()) {
    val meals by mealViewModel.meals.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Meals") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(meals) { meal ->
                MealItem(meal)
            }
        }
    }
}

@Composable
fun MealItem(meal: Meal) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = meal.meal ?: "Unknown Meal", style = MaterialTheme.typography.titleMedium)
            Text(text = "Category: ${meal.category}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Area: ${meal.area}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
