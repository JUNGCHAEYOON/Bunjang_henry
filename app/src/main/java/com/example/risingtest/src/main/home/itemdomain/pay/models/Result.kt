package com.example.risingtest.src.main.home.itemdomain.pay.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("finalPrice")
    val finalPrice: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?
)