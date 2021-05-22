package com.biodun.roomassignment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biodun.roomassignment.databinding.CategoryRecyclerItemBinding

class CategoryAdapter (context: Context, private val categories: ArrayList<Category>, val clickListener: (Category) -> Unit):
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

//    private val categories = category


    inner class CategoryViewHolder ( private val binding: CategoryRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCategoryItem (category: Category, clickListener: (Category) -> Unit){
            binding.categoryNameTv.text = category.categoryName
            binding.categoryNameTv.setOnClickListener { clickListener (category) }

            binding.categoryIv.setImageResource(category.categoryIcon)
            binding.categoryIv.setOnClickListener { clickListener (category) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(CategoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bindCategoryItem(category, clickListener)

    }

}
