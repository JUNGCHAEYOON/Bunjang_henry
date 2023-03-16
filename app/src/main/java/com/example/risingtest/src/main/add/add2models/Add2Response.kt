package com.example.risingtest.src.main.add.add2models


import com.google.gson.annotations.SerializedName

data class Add2Response(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: Result?
)