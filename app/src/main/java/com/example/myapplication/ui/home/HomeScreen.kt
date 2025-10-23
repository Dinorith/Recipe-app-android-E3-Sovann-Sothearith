package com.example.myapplication.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.Meal
import com.example.myapplication.viewmodel.MealViewModel

@Composable
fun HomeScreen(viewModel: MealViewModel, navController: NavController) {
    val meals by viewModel.meals.collectAsState()
    val categories by viewModel.categories.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F9FC))
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Header
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFF1565C0), Color(0xFF42A5F5))
                            )
                        )
                        .padding(horizontal = 24.dp, vertical = 40.dp)
                ) {
                    Column {
                        Text(
                            text = "Discover Recipes 🍳",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Find meals that inspire your kitchen every day.",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color(0xFFE3F2FD),
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }

            // Categories
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        text = "Explore Categories",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        ),
                        color = Color(0xFF0D47A1)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        categories.forEach { category ->
                            CategoryChip(category)
                        }
                    }
                }
            }

            // Popular Meals Section
            item {
                Text(
                    text = "Popular Meals",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    ),
                    color = Color(0xFF1A1A1A),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            // Meals list
            items(meals) { meal ->
                Box(modifier = Modifier.padding(horizontal = 20.dp)) {
                    MealCard(meal) { navController.navigate("mealDetail/${meal.id}") }
                }
            }
        }
    }
}

@Composable
fun MealCard(meal: Meal, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .shadow(8.dp, RoundedCornerShape(20.dp))
    ) {
        val imageUrl = meal.mealThumb ?: ""
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = meal.meal,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Gradient overlay for text visibility
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 100f
                    )
                )
        )

        // Text content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = meal.meal ?: "Unknown Meal",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = meal.category ?: "Uncategorized",
                color = Color(0xFFBBDEFB),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Composable
fun CategoryChip(category: Category) {
    Surface(
        modifier = Modifier
            .wrapContentWidth()
            .height(42.dp)
            .clip(RoundedCornerShape(50))
            .clickable { /* category click logic */ }
            .shadow(2.dp, RoundedCornerShape(50)),
        color = Color(0xFF1976D2).copy(alpha = 0.12f),
        tonalElevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = category.category ?: "Category",
                color = Color(0xFF1976D2),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
            )
        }
    }
}
