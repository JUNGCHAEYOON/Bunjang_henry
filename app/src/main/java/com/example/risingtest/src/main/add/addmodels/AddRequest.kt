package com.example.risingtest.src.main.add.addmodels

data class AddRequest(
    val amount: Int?,
    val checkExchange: String?,
    val checkNewProduct: String?,
    val checkPay: String?,
    val content: String?,
    val hasDeliveryFee: String?,
    val majorCategoryId: Int?,
    val middleCategoryId: Int?,
    val price: Int?,
    val region: String?,
    val subCategoryId: Int?,
    val tagIds: List<Int?>?,
    val title: String?,
    val userId: Int?
)