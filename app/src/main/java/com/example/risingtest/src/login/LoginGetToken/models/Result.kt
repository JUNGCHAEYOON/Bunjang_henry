package com.example.risingtest.src.login.LoginGetToken.models

data class Result(
    val jwt: String?,
    val name: String?,
    val storeName: String?,
    val userId: Int?
)