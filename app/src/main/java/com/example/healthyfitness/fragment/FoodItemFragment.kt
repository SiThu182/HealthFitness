package com.example.healthyfitness.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyfitness.R
import com.example.healthyfitness.adapter.FoodAdapter
import com.example.healthyfitness.adapter.FoodItemAdapter
import com.example.healthyfitness.viewmodel.FoodCategoriesViewModel
import kotlinx.android.synthetic.main.fragment_food_item.*

class FoodItemFragment : Fragment() {

    private lateinit var foodItemViewModel : FoodCategoriesViewModel
    private lateinit var foodAdapter: FoodItemAdapter
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        foodAdapter = FoodItemAdapter()
        rcyFoodItem.apply {
            adapter = foodAdapter
            layoutManager= LinearLayoutManager(context)
        }
        observeViewModel()
    }


    override fun onResume() {
        super.onResume()
        var data = arguments?.let { FoodItemFragmentArgs.fromBundle(it!!) }
        var foodId = data?.FoodItemDetailId
        var categoryName = data?.CategoryName
        (activity as AppCompatActivity).supportActionBar?.title = categoryName
        foodItemViewModel.loadFoodItemsResult(foodId!!)
    }
    private fun observeViewModel() {
        foodItemViewModel = ViewModelProvider(this).get(FoodCategoriesViewModel::class.java)
        foodItemViewModel.getDetails().observe(
            viewLifecycleOwner, Observer {
                foodAdapter.updateFoodItemList(it.food)
            }
        )
    }
}