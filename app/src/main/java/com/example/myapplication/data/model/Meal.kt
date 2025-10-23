package com.example.myapplication.data.model

data class Meal(
    val id: String,
    val meal: String?,
    val drinkAlternate: String?,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val mealThumb: String?,
    val tags: String?,
    val youtube: String?,
    val source: String?,
    val ingredients: List<Ingredient>?,
    val categoryId: String?
)