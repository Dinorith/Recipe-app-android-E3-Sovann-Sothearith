package com.example.myapplication.data.local

import androidx.room.*
import com.example.myapplication.data.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMealDao {

    @Query("SELECT * FROM favorite_meals")
    fun getAllFavorites(): Flow<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(meal: Meal)

    @Delete
    suspend fun deleteFavorite(meal: Meal)
}
