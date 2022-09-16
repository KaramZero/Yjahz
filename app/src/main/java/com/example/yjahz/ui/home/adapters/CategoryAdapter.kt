package com.example.yjahz.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yjahz.databinding.CategoryItemBinding
import com.example.yjahz.model.Category


class CategoryAdapter(val context: Context) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var categoryList: ArrayList<Category> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = categoryList[position]
        holder.binding.apply {
            titleTextView.text = currentItem.nameEn
            Glide.with(context)
                .load(currentItem.image)
                .into(productImageView)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.count()
    }

    fun setData(categoryList: ArrayList<Category>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

}
