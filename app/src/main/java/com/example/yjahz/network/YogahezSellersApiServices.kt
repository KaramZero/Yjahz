package com.example.yjahz.network

import com.example.yjahz.model.SellerResponse
import com.example.yjahz.model.CategoryResponse
import retrofit2.http.*

interface YogahezSellersApiServices {

    @GET("popular-sellers?lat=29.1931&lng=30.6421&filter=1")
    suspend fun getPopularSellers(): SellerResponse

    @GET("trending-sellers?lat=29.1931&lng=30.6421&filter=1")
    suspend fun getTrendingSellers(): SellerResponse

    @GET("home-base-categories")
    suspend fun getCategories(): CategoryResponse

}