package com.example.yjahz.remote_source

import android.util.Log
import com.example.yjahz.model.Category
import com.example.yjahz.model.seller.Seller
import com.example.yjahz.model.user.User
import com.example.yjahz.network.Keys
import com.example.yjahz.network.RetrofitHelper
import com.example.yjahz.network.YogahezAuthApiServices
import com.example.yjahz.network.YogahezSellersApiServices
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

class SellersRemoteSource private constructor(){

    private val gson = Gson()
    private val api = RetrofitHelper.getInstance().create(YogahezSellersApiServices::class.java)

    companion object {
        private var remoteSource: SellersRemoteSource? = null
        fun getInstance(): SellersRemoteSource {
            return remoteSource ?: SellersRemoteSource()
        }
    }


    suspend fun getPopularSellers(): ArrayList<Seller> {
        val res = api.getPopularSellers()
        Log.d("TAG", "getPopularSellers: $res ")
        return gson.fromJson(
            res.body()!!.get("data")as JsonArray,
            object : TypeToken<ArrayList<Seller>>() {}.type
        )
    }

    suspend fun getTrendingSellers(): ArrayList<Seller> {
        val res = api.getTrendingSellers()
        Log.d("TAG", "getTrendingSellers: $res ")
        return gson.fromJson(
            res.body()!!.get("data")as JsonArray,
            object : TypeToken<ArrayList<Seller>>() {}.type
        )
    }

    suspend fun getCategories(): ArrayList<Category> {
        val res = api.getCategories()
        Log.d("TAG", "getCategories: $res ")
        return gson.fromJson(
            (res.body()!!.get("data") as JsonObject ).get("data") as JsonArray,
            object : TypeToken<ArrayList<Category>>() {}.type
        )
    }



}