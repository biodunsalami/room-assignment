package com.biodun.roomassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.biodun.roomassignment.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

//    var binding: ActivityMainBinding? = null
//    var adapter: CategoryAdapter? = null

    private lateinit var binding: ActivityCategoryBinding
    var adapter: CategoryAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_category)
        setContentView(binding.root)
//        binding.categoryTb.title = "Category"


        setUpCategories(binding)
    }

    private fun setUpCategories(binding: ActivityCategoryBinding) {
        adapter = CategoryAdapter(this, CategoryData.categories, {category: Category ->
            clickedCategoryViews(category)
        })

//        Log.e("VeryKinky", CategoryData.categories.toString())
        val recyclerView = binding.categoryRv

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter



//        binding.categoryRv.adapter = adapter

    }

    private fun clickedCategoryViews ( clickedView : Category) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("categoryToolbarTitle", clickedView.categoryName )
        startActivity(intent)
    }
}