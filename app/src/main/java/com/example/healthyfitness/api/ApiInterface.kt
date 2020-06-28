package com.example.healthyfitness.api

import com.example.healthyfitness.model.Category
import com.example.healthyfitness.model.Food
import com.example.healthyfitness.model.FoodCategories
import com.example.healthyfitness.model.FoodItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("category")
    fun getCategories(): Call<FoodCategories>

    @GET("filterByCategory")
    fun getFoodItemDetails(
        @Query("category_id") categoryId : Int
    ): Call<FoodItem>


}