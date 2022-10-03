package com.example.yjahz.model

import com.example.yjahz.model.seller.Seller
import com.google.gson.annotations.SerializedName


data class SellerResponse (

  @SerializedName("success"       ) var success      : Boolean?          = null,
  @SerializedName("response_code" ) var responseCode : Int?              = null,
  @SerializedName("message"       ) var message      : String?           = null,
  @SerializedName("data"          ) var sellers      : ArrayList<Seller> = arrayListOf()

)