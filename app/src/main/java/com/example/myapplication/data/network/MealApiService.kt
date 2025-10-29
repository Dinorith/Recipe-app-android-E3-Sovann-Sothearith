package com.example.myapplication.data.network

import com.example.myapplication.data.model.Meal
import com.example.myapplication.data.model.Category
import retrofit2.http.GET
import retrofit2.http.Header

interface MealApiService {
    @GET("meals")
    suspend fun getMeals(
        @Header("X-DB-NAME") dbName: String = "2cb2ab5c-1c69-4377-b112-ea8496de8e9d"
    ): List<Meal>

    @GET("categories")
    suspend fun getCategories(
        @Header("X-DB-NAME") dbName: String = "2cb2ab5c-1c69-4377-b112-ea8496de8e9d"
    ): List<Category>
}
