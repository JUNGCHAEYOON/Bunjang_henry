package com.example.risingtest.src.main.home.itemdomain.pay.models


import com.google.gson.annotations.SerializedName

data class PayRequest(
    @SerializedName("agreement")
    val agreement: Boolean?,
    @SerializedName("commisionPrice")
    val commisionPrice: Int?,
    @SerializedName("deliveryAddressId")
    val deliveryAddressId: Int?,
    @SerializedName("deliveryRequest")
    val deliveryRequest: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("finalPrice")
    val finalPrice: Int?,
    @SerializedName("paymentMethod")
    val paymentMethod: String?,
    @SerializedName("usedPoint")
    val usedPoint: Int?,
    @SerializedName("userId")
    val userId: Int?
)