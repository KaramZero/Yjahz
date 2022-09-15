package com.example.yjahz.network

import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface YogahezAuthApiServices {

    @GET(Keys.ClientProfileEndPoint)
    suspend fun getClientProfile(@HeaderMap headers: Map<String, String>): Response<JsonObject>


    @POST(Keys.LoginEndPoint)
    suspend fun logIn(@Body requestBody: RequestBody): Response<JsonObject>

    @POST(Keys.SignUpEndPoint)
    suspend fun signUp(@HeaderMap headers: Map<String, String>,@Body requestBody: RequestBody): Response<JsonObject>

}