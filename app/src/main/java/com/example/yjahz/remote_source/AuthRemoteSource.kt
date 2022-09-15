package com.example.yjahz.remote_source

import android.util.Log
import com.example.yjahz.model.pojo.User
import com.example.yjahz.network.Keys
import com.example.yjahz.network.RetrofitHelper
import com.example.yjahz.network.YogahezAuthApiServices
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AuthRemoteSource private constructor(){

    private val gson = Gson()
    private val api = RetrofitHelper.getInstance().create(YogahezAuthApiServices::class.java)

    companion object {
        private var remoteSource: AuthRemoteSource? = null
        fun getInstance(): AuthRemoteSource {
            return remoteSource ?: AuthRemoteSource()
        }
    }

    suspend fun getClientProfile(): User {
        val res = api.getClientProfile(Keys.headers)
        Log.d("TAG", "getClientProfile: ${res} ")
        return gson.fromJson(
            res.body()!!.get("data")as JsonObject,
            object : TypeToken<User>() {}.type
        )
    }

    suspend fun logIn(email: String,password: String):User{
        val res = api.logIn(getLogInRequestBody(email,password))

        Log.e("TAG", "logIn:  ${res.body()!!.get("success")}", )

        if (res.body()!!.get("success").toString() == "false") throw Exception("Wrong data")
        return gson.fromJson(
            res.body()!!.get("data")as JsonObject,
            object : TypeToken<User>() {}.type
        )
    }

    suspend fun signUp(name:String,email:String ="null",password:String="null",phone:String):User{
        val res = api.signUp(Keys.headers,getSignUpRequestBody(name,email,password,phone))

        Log.e("TAG", "logIn:  ${res.body()!!.get("success")}", )

        if (res.body()!!.get("success").toString() == "false") throw Exception(res.body()!!.get("message").toString())
        return gson.fromJson(
            res.body()!!.get("data")as JsonObject,
            object : TypeToken<User>() {}.type
        )
    }

    private fun getLogInRequestBody(email:String,password:String): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("email", email)
        jsonReq.put("password", password)
        return jsonReq.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

    private fun getSignUpRequestBody(name:String,email:String ="null",password:String="null",phone:String): RequestBody {

        val jsonReq = JSONObject()
        jsonReq.put("name", name)
        jsonReq.put("email", email)
        jsonReq.put("password", password)
        jsonReq.put("phone", phone)
        return jsonReq.toString().toRequestBody("application/json".toMediaTypeOrNull())
    }

}