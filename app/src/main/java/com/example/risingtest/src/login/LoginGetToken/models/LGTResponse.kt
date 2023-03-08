package com.example.risingtest.src.login.LoginGetToken.models

data class LGTResponse(
    val code: Int?,
    val isSuccess: Boolean?,
    val message: String?,
    val result: Result?
)