package com.example.risingtest.src.main.home.listdomain

data class LDItem(
    var categorytitle: String?,
    var id: Int?,
    var price: Int? = 0,
    var safeCare: Boolean? = false,
    var safePay: Boolean? = false,
    var title: String?,
    var url: String?
)