package com.example.yjahz.di

import com.example.yjahz.network.YogahezAuthApiServices
import com.example.yjahz.network.YogahezSellersApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideSellersApiService(retrofit: Retrofit) : YogahezSellersApiServices = retrofit.create(
        YogahezSellersApiServices::class.java)

    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit) : YogahezAuthApiServices = retrofit.create(
        YogahezAuthApiServices::class.java)

}

