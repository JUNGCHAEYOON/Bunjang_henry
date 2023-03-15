package com.example.risingtest.src.main.home.itemdomain.models

data class Result(
    val amount: Int?,
    val brandImgUrl: String?,
    val brandName: String?,
    val categoryImgUrl: Any?,
    val categoryTitle: String?,
    val chatCounts: Int?,
    val content: String?,
    val createdAt: String?,
    //2개추가
    val dayCreatedFrom : Int?,
    val hourCreatedFrom : Int?,

    val hasDeliveryFee: String?,
    val isInterchangeable: String?,
    val isNew: String?,
    val isSafePay: String?,
    val likes: Int?,
    val locationAddress: String?,
    val price: Int?,
    val productImgUrl: List<String?>?,
    val tags: List<Any?>?,
    val title: String?,
    val view: Int?
)