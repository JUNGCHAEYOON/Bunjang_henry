package com.example.risingtest.src.main.home.itemdomain.pay.models


import com.google.gson.annotations.SerializedName

data class PayResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: Result?
)