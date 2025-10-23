package com.example.myapplication.ui.onboarding

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val imageUrl =
        "https://marketplace.canva.com/EAGN6tjw2w8/1/0/1600w/canva-orange-and-yellow-kitchen-food-logo-2G4Q5SFCJ28.jpg"

    // Animation for slight floating image effect
    var isScaled by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (isScaled) 1.05f else 1f, label = "scale")

    LaunchedEffect(Unit) {
        while (true) {
            isScaled = !isScaled
            kotlinx.coroutines.delay(2000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFBBDEFB),
                        Color(0xFFE3F2FD),
                        Color(0xFFFFFFFF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Skip button (top right)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Text(
                    text = "Skip",
                    color = Color(0xFF1976D2),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .clickable { navController.navigate("home") }
                        .padding(8.dp)
                )
            }

            // Hero image
            Card(
                modifier = Modifier
                    .size(260.dp)
                    .scale(scale),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Healthy food illustration",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Text section
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Welcome to",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp
                    ),
                    color = Color(0xFF1565C0)
                )

                Text(
                    text = "Recipe Finder",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 38.sp
                    ),
                    color = Color(0xFF0D47A1)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Cook smart, eat healthy.\nFind delicious recipes that fit your taste.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 17.sp,
                        lineHeight = 26.sp
                    ),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF424242),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // Button section
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = { navController.navigate("home") },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                ) {
                    Text(
                        text = "Get Started",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Indicator dots
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FeatureDot(isActive = true)
                    Spacer(modifier = Modifier.width(8.dp))
                    FeatureDot(isActive = false)
                    Spacer(modifier = Modifier.width(8.dp))
                    FeatureDot(isActive = false)
                }
            }
        }
    }
}

@Composable
private fun FeatureDot(isActive: Boolean) {
    Box(
        modifier = Modifier
            .size(if (isActive) 12.dp else 8.dp)
            .clip(CircleShape)
            .background(if (isActive) Color(0xFF1976D2) else Color(0xFFBBDEFB))
    )
}
