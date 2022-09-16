package com.example.yjahz.ui.home.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yjahz.databinding.PopularItemBinding
import com.example.yjahz.model.seller.Seller
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class PopularSellerAdapter @Inject constructor(@ActivityContext val context: Context) :
    RecyclerView.Adapter<PopularSellerAdapter.ViewHolder>() {
    private var sellerList: ArrayList<Seller> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = sellerList[position]

        holder.binding.apply {
            titleTxt.text = currentItem.name
            ratingBar.setRating(currentItem.rate?.toFloat() ?: 5.0f)
            ratingTextView.text = currentItem.rate
            val dis = "${currentItem.distance?.toInt()?.div(100)?.div(10.0)} Km"
            distance.text = dis
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

    class ViewHolder(val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root)

}
