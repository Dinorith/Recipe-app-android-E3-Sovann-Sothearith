package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.Meal
import com.example.myapplication.data.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealViewModel(private val repo: MealRepository) : ViewModel() {

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: StateFlow<List<Meal>> = _meals

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _favorites = MutableStateFlow<List<Meal>>(emptyList())
    val favorites: StateFlow<List<Meal>> = _favorites

    init {
        viewModelScope.launch {
            _meals.value = repo.getMeals()
            _categories.value = repo.getCategories()
        }
    }

    fun toggleFavorite(meal: Meal) {
        _favorites.value = if (_favorites.value.contains(meal)) {
            _favorites.value - meal
        } else {
            _favorites.value + meal
        }
    }
}