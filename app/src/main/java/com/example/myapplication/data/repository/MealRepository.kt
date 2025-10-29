package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.Meal
import com.example.myapplication.data.network.RetrofitInstance

class MealRepository {
    private val api = RetrofitInstance.api

    suspend fun getMeals(): List<Meal> = api.getMeals()

    suspend fun getCategories(): List<Category> = api.getCategories()
}
