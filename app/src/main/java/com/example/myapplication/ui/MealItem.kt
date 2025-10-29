package com.example.myapplication.uiimport
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.Meal

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
            Text(text = "Category: ${meal.category ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Area: ${meal.area ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Ingredients:", style = MaterialTheme.typography.titleSmall)
            meal.ingredients?.forEach { ingredient ->
                val name = ingredient.ingredient ?: "Unknown"
                val measure = ingredient.measure ?: ""
                Text(text = "- $name: $measure", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

