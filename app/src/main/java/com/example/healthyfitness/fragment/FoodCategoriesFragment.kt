package com.example.healthyfitness.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyfitness.R
import com.example.healthyfitness.adapter.FoodAdapter
import com.example.healthyfitness.model.Category
import com.example.healthyfitness.viewmodel.FoodCategoriesViewModel
import kotlinx.android.synthetic.main.fragment_foodcategories.*

class FoodCategoriesFragment : Fragment() ,FoodAdapter.ClickListener{

    private lateinit var foodViewModel: FoodCategoriesViewModel
    private lateinit var votingAdapter: FoodAdapter
    private lateinit var viewManager  : RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_foodcategories, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(context)
        votingAdapter = FoodAdapter()//no need to add constructor cause of we defined array list
        rcyFoodCategories.apply {
            adapter= votingAdapter
            layoutManager = viewManager
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        foodViewModel = ViewModelProvider(this).get(FoodCategoriesViewModel::class.java)//create VM object
        foodViewModel.categoryResults.observe(
            viewLifecycleOwner, Observer {
                tvError.visibility = View.GONE
                rcyFoodCategories.visibility = View.VISIBLE
                votingAdapter.updateList(it)

                Log.d("UPdateList>>>",it.toString())
                votingAdapter.setClickListener(this)
            }
        )

        foodViewModel.getError().observe(
            viewLifecycleOwner, Observer {
                if (it) {
                    tvError.visibility = View.VISIBLE
                    rcyFoodCategories.visibility = View.GONE
                } else {
                    tvError.visibility = View.GONE
                    rcyFoodCategories.visibility = View.VISIBLE
                }
            }
        )
    }


    override fun onResume() {
        super.onResume()
        foodViewModel.loadCategoriesResult()
    }

    override fun onClick(category: Category) {
        //foodViewModel.loadFoodItemsResult(id)
        var action = FoodCategoriesFragmentDirections.actionNavFoodToFoodItemsFragment(category.id,category.category_name)
        findNavController().navigate(action)
        Log.d("detailID>>>>>>>",id.toString())
    }


}