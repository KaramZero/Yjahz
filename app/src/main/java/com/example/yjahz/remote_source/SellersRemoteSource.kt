package com.example.yjahz.remote_source

import com.example.yjahz.model.Category
import com.example.yjahz.model.seller.Seller
import com.example.yjahz.network.YogahezSellersApiServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SellersRemoteSource @Inject constructor() {

    @Inject
    lateinit var api: YogahezSellersApiServices

    suspend fun getPopularSellers(): ArrayList<Seller> {
        return api.getPopularSellers().sellers
    }

    suspend fun getTrendingSellers(): ArrayList<Seller> {
        return api.getTrendingSellers().sellers
    }

    suspend fun getCategories(): ArrayList<Category> {
        return api.getCategories().categoryData!!.categories
    }

}