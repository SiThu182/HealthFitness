package com.example.healthyfitness.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyfitness.R
import com.example.healthyfitness.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_food_categories.view.*
import java.util.*

class FoodAdapter: RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){

    var mClickListener: ClickListener? = null

    fun setClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    var categoryList: List<Category> = listOf()

    inner class FoodViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener
    {
        private lateinit var category : Category

        init {
            itemView.setOnClickListener(this)//initialize onClick fun:s and/ when start create obj,it works
        }

        fun bindMovie(category:Category){
            var base_url = "https://healthfitness.khaingthinkyi.me/"
            this.category = category
            Picasso.get().load("$base_url${category.category_image}")
                .placeholder(R.drawable.ic_launcher_background).into(itemView.imgFoodCategories)//place holder is to show temp image when loading
            itemView.tvFoodCategoriesName.text = category.category_name

            Log.d("Image>>>>",itemView.imgFoodCategories.toString())
        }

        override fun onClick(view: View) {
            mClickListener?.onClick(category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_categories,parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
        Log.d("new>>>>>",categoryList.size.toString())
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bindMovie(categoryList[position])
    }

    fun updateList(categories : List<Category>)
    {
        this.categoryList = categories
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(category : Category )
    }

}