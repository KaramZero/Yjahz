package com.example.yjahz.network

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface YogahezSellersApiServices {

    @GET("popular-sellers?lat=29.1931&lng=30.6421&filter=1")
    suspend fun getPopularSellers(): Response<JsonObject>

    @GET("trending-sellers?lat=29.1931&lng=30.6421&filter=1")
    suspend fun getTrendingSellers(): Response<JsonObject>

    @GET("home-base-categories")
    suspend fun getCategories(): Response<JsonObject>

}