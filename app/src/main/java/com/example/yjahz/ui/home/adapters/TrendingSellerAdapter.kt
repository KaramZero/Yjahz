
package com.example.yjahz.ui.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yjahz.databinding.TrendingItemBinding
import com.example.yjahz.model.seller.Seller
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class TrendingSellerAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<TrendingSellerAdapter.ViewHolder>() {
    private var sellerList: ArrayList<Seller> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TrendingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = sellerList[position]
        holder.binding.apply {
            Glide.with(context)
                .load(currentItem.image)
                .into(productImageView)
        }

    }

    override fun getItemCount(): Int {
        return sellerList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(sellerList: ArrayList<Seller>) {
        this.sellerList = sellerList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: TrendingItemBinding) : RecyclerView.ViewHolder(binding.root)

}
