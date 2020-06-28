package com.example.healthyfitness.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyfitness.R
import com.example.healthyfitness.model.Category
import com.example.healthyfitness.model.Food
import com.example.healthyfitness.model.FoodItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_food.view.*
import kotlinx.android.synthetic.main.item_food_categories.view.*

class FoodItemAdapter: RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>(){

   /* var mClickListener: ClickListener? = null

    fun setClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }*/

    var foodItemList: List<Food> = listOf()

    inner class FoodItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        fun bindMovie(foodItem: Food){
            var base_url = "https://healthfitness.khaingthinkyi.me/"
            Picasso.get().load("$base_url${foodItem.food_image}")
                .placeholder(R.drawable.ic_launcher_background).into(itemView.imgItemFood)//place holder is to show temp image when loading
            itemView.tvItemFoodName.text = foodItem.food_name
            itemView.tvItemFoodCalorie.text = foodItem.calories

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        return FoodItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodItemList.size
        Log.d("new>>>>>",foodItemList.size.toString())
    }

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        holder.bindMovie(foodItemList[position])
    }

    fun updateFoodItemList(foodItem : List<Food>)
    {
        this.foodItemList = foodItem
        notifyDataSetChanged()
    }
}