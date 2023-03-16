package com.example.risingtest.src.main.add.add2models


import com.google.gson.annotations.SerializedName

data class Add2Request(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("checkExchange")
    val checkExchange: String?,
    @SerializedName("checkNewProduct")
    val checkNewProduct: String?,
    @SerializedName("checkPay")
    val checkPay: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("hasDeliveryFee")
    val hasDeliveryFee: String?,
    @SerializedName("majorCategoryId")
    val majorCategoryId: Int?,
    @SerializedName("middleCategoryId")
    val middleCategoryId: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("subCategoryId")
    val subCategoryId: Int?,
    @SerializedName("tagIds")
    val tagIds: List<Int?>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
)