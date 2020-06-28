package com.example.healthyfitness.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthyfitness.api.Api
import com.example.healthyfitness.model.Category
import com.example.healthyfitness.model.Food
import com.example.healthyfitness.model.FoodCategories
import com.example.healthyfitness.model.FoodItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class FoodCategoriesViewModel : ViewModel() {

    var categoryResults: MutableLiveData<List<Category>> = MutableLiveData()
    var detailResult: MutableLiveData<FoodItem> = MutableLiveData()
    var resultLoadError : MutableLiveData<Boolean> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()

    fun getResults(): LiveData<List<Category>> = categoryResults
    fun getDetails():LiveData<FoodItem> = detailResult
    fun getError() : LiveData<Boolean> = resultLoadError
    fun getLoading() : LiveData<Boolean> = loading

    private val votingApi: Api = Api()

    fun loadCategoriesResult() {
        loading.value = true
        val apiCall = votingApi.getCategories()
        apiCall.enqueue(object : Callback<FoodCategories> {

            override fun onFailure(call: Call<FoodCategories>, t: Throwable) {
                resultLoadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<FoodCategories>, response: Response<FoodCategories>) {
                response.isSuccessful.let {
                    loading.value = false
                    val resultList:List<Category> = response.body()?.categories?: emptyList()
                    categoryResults.value = resultList
                }
            }
        })
    }

    fun loadFoodItemsResult(id: Int) {
        loading.value = true
        val apiCall = votingApi.getFoodItem(id)
        apiCall.enqueue(object : Callback<FoodItem> {

            override fun onFailure(call: Call<FoodItem>, t: Throwable) {
                resultLoadError.value = true
                loading.value = false
            }

            override fun onResponse(call: Call<FoodItem>, response: Response<FoodItem>) {
                response.isSuccessful.let {
                    loading.value = false
                    detailResult.value = response.body()
                    Log.d("DetailResult>>>>>",response.body().toString())
                }
            }
        })
    }
}