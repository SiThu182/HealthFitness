package com.example.healthyfitness.api

import android.util.Log
import com.example.healthyfitness.model.Food
import com.example.healthyfitness.model.FoodCategories
import com.example.healthyfitness.model.FoodItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private val apiInterface: ApiInterface

    companion object {
        const val Base_URL = "https://healthfitness.khaingthinkyi.me/api/"
    }

    init {
        //when create class obj and it create retrofits
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getCategories(): Call<FoodCategories> {
        return apiInterface.getCategories()
    }

    fun getFoodItem(id: Int): Call<FoodItem>{
        Log.d("FoodID>>>>",id.toString())
        return apiInterface.getFoodItemDetails(id)

    }
}