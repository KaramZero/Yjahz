/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.yjahz.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yjahz.databinding.PopularItemBinding
import com.example.yjahz.databinding.TrendingItemBinding
import com.example.yjahz.model.seller.Seller


class TrendingSellerAdapter(val context: Context) :
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

    fun setData(sellerList: ArrayList<Seller>) {
        this.sellerList = sellerList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: TrendingItemBinding) : RecyclerView.ViewHolder(binding.root)

}
