package com.example.yjahz.remote_source

import com.example.yjahz.model.Category
import com.example.yjahz.model.seller.Seller
import com.example.yjahz.network.YogahezSellersApiServices
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SellersRemoteSource @Inject constructor(){

    @Inject lateinit var gson : Gson
    @Inject lateinit var api : YogahezSellersApiServices


    suspend fun getPopularSellers(): ArrayList<Seller> {
        val res = api.getPopularSellers()

        return gson.fromJson(
            res.body()!!.get("data")as JsonArray,
            object : TypeToken<ArrayList<Seller>>() {}.type
        )
    }

    suspend fun getTrendingSellers(): ArrayList<Seller> {
        val res = api.getTrendingSellers()

        return gson.fromJson(
            res.body()!!.get("data")as JsonArray,
            object : TypeToken<ArrayList<Seller>>() {}.type
        )
    }

    suspend fun getCategories(): ArrayList<Category> {
        val res = api.getCategories()

        return gson.fromJson(
            (res.body()!!.get("data") as JsonObject ).get("data") as JsonArray,
            object : TypeToken<ArrayList<Category>>() {}.type
        )
    }

}