package com.example.risingtest.src.login.LoginGetToken.models

data class LGTRequest(
    val name: String?,
    val password: String?,
    val phoneNumber: String?,
    val uid: String?
)