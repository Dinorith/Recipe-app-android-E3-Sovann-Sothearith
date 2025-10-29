package com.example.myapplication.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

// Color palette — warm, inviting tones for a foodie app
val PrimaryColor = Color(0xFFFF7043) // Vibrant orange
val SecondaryColor = Color(0xFF4CAF50) // Fresh green
val BackgroundLight = Color(0xFFFFFFFF)
val TextDark = Color(0xFF212121)
val TextLight = Color(0xFF757575)

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val imageUrl =
        "https://marketplace.canva.com/EAGN6tjw2w8/1/0/1600w/canva-orange-and-yellow-kitchen-food-logo-2G4Q5SFCJ28.jpg"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.1f))

            // Image
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Cooking illustration",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(32.dp))
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Text content
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Cook. Taste. Share.",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = PrimaryColor,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Explore easy-to-follow recipes made by real food lovers. Turn everyday ingredients into something unforgettable.",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 18.sp,
                        lineHeight = 28.sp
                    ),
                    color = TextLight,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Feature dots
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FeatureDot(isActive = true, color = SecondaryColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    FeatureDot(isActive = false, color = SecondaryColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    FeatureDot(isActive = false, color = SecondaryColor)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 48.dp)
            ) {
                Button(
                    onClick = { navController.navigate("home") },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 10.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Text(
                        text = "Let’s Get Cooking 🍳",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun FeatureDot(isActive: Boolean, color: Color) {
    Box(
        modifier = Modifier
            .size(if (isActive) 10.dp else 8.dp)
            .clip(CircleShape)
            .background(
                if (isActive) color else color.copy(alpha = 0.3f)
            )
    )
}
