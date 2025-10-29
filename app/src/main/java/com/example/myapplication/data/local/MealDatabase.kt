package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMeal::class], version = 1)
abstract class MealDatabase : RoomDatabase() {
    abstract fun favoriteMealDao(): FavoriteMealDao
}