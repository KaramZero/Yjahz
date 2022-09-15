package com.example.yjahz.di

import android.app.Application
import android.content.Context

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//
//@Module
//@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
 //   @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("user", Context.MODE_PRIVATE)
    }
}