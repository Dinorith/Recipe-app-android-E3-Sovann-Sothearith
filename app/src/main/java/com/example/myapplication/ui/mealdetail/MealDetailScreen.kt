package com.example.myapplication.ui.mealdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealDetailScreen(mealId: String, viewModel: MealViewModel, navController: NavController) {
    val meals by viewModel.meals.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val meal = meals.find { it.id == mealId } ?: return

    val isFavorite = favorites.contains(meal)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9FAFB)),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            // Header Image Section
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                        .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(meal.mealThumb),
                        contentDescription = meal.meal,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Gradient overlay for readability
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Black.copy(alpha = 0.5f),
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.7f)
                                    )
                                )
                            )
                    )

                    // Meal title
                    Text(
                        text = meal.meal ?: "",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 30.sp,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(24.dp)
                    )
                }
            }

            // Content Section
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 24.dp)
                ) {
                    // Category & Area tags
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TagChip(text = meal.category ?: "Unknown", color = Color(0xFF1976D2))
                        TagChip(text = meal.area ?: "N/A", color = Color(0xFFFF7043))
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    // Instructions Section
                    SectionTitle(title = "Instructions")
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = meal.instructions ?: "",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            lineHeight = 26.sp
                        ),
                        color = Color(0xFF424242)
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    // Ingredients Section
                    SectionTitle(title = "Ingredients")
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            // Ingredients list
            items(meal.ingredients ?: emptyList()) { ingredient ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(14.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ingredient.ingredient ?: "",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            ),
                            color = Color(0xFF212121),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = ingredient.measure ?: "",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFF757575)
                            )
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }

        // Top Floating Buttons (Back + Favorite)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopStart),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CircleButton(
                icon = Icons.Default.ArrowBack,
                tint = Color(0xFF212121),
                onClick = { navController.popBackStack() }
            )

            CircleButton(
                icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                tint = if (isFavorite) Color(0xFFFF5252) else Color(0xFF212121),
                onClick = { viewModel.toggleFavorite(meal) }
            )
        }
    }
}

@Composable
fun TagChip(text: String, color: Color) {
    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = text,
            color = color,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),
        color = Color(0xFF212121)
    )
}

@Composable
fun CircleButton(icon: androidx.compose.ui.graphics.vector.ImageVector, tint: Color, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.White,
        contentColor = tint,
        modifier = Modifier
            .size(50.dp)
            .shadow(10.dp, CircleShape)
    ) {
        Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(26.dp))
    }
}
