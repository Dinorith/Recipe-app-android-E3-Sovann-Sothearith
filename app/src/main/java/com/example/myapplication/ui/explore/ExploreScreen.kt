package com.example.myapplication.ui.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.model.Meal
import com.example.myapplication.viewmodel.MealViewModel

@Composable
fun ExploreScreen(viewModel: MealViewModel, navController: NavController) {
    val categories by viewModel.categories.collectAsState()
    val meals by viewModel.meals.collectAsState()
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredMeals = meals.filter { meal ->
        (selectedCategory == null || meal.category == selectedCategory) &&
                (searchQuery.isEmpty() || meal.meal?.contains(searchQuery, ignoreCase = true) == true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF6FAFF), Color(0xFFE8F0FE))
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Header with Search Bar
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color(0xFF1E88E5), Color(0xFF64B5F6))
                            )
                        )
                        .padding(horizontal = 24.dp, vertical = 40.dp)
                ) {
                    Text(
                        text = "Explore Recipes 🍽️",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Search and discover your next favorite meal.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Search Field
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search recipes...", color = Color.White.copy(0.8f)) },
                        shape = RoundedCornerShape(50),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.White,
                            unfocusedBorderColor = Color.White.copy(0.6f),
                            cursorColor = Color.White
                        )
                    )
                }
            }

            // Category Filter
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        text = "Categories",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0D47A1)
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        GradientChip(
                            text = "All",
                            selected = selectedCategory == null,
                            onClick = { selectedCategory = null }
                        )
                        categories.forEach { category ->
                            GradientChip(
                                text = category.category ?: "Unknown",
                                selected = selectedCategory == category.category,
                                onClick = {
                                    selectedCategory =
                                        if (selectedCategory == category.category) null
                                        else category.category
                                }
                            )
                        }
                    }
                }
            }

            // Results Count
            if (filteredMeals.isNotEmpty()) {
                item {
                    Text(
                        text = "Found ${filteredMeals.size} recipes",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF616161)
                        ),
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }

            // Meals List
            items(filteredMeals) { meal ->
                ExploreMealCard(meal) {
                    navController.navigate("mealDetail/${meal.id}")
                }
            }

            // Empty State
            if (filteredMeals.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 80.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Recipes Found 🍳",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            ),
                            color = Color(0xFF424242)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Try adjusting your filters or search.",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp),
                            color = Color(0xFF9E9E9E)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GradientChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        shape = CircleShape,
        shadowElevation = if (selected) 6.dp else 2.dp,
        color = Color.Transparent,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (selected)
                        Brush.horizontalGradient(listOf(Color(0xFF42A5F5), Color(0xFF1E88E5)))
                    else Brush.horizontalGradient(listOf(Color.White, Color.White))
                )
                .padding(horizontal = 18.dp, vertical = 10.dp)
        ) {
            Text(
                text = text,
                color = if (selected) Color.White else Color(0xFF424242),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun ExploreMealCard(meal: Meal, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Image Section
            Image(
                painter = rememberAsyncImagePainter(meal.mealThumb ?: ""),
                contentDescription = meal.meal,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            )

            // Text Section
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = meal.meal ?: "Unknown Meal",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    color = Color(0xFF1A1A1A)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = meal.category ?: "Category",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF1976D2),
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Tap to view recipe →",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF616161)
                    )
                )
            }
        }
    }
}
