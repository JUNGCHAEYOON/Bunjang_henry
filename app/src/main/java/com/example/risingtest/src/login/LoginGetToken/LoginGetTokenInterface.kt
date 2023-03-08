package com.example.risingtest.src.login.LoginGetToken

import com.example.risingtest.src.login.LoginGetToken.models.LGTResponse

interface LoginGetTokenInterface {
    fun onPostLoginGetTokenSuccess(response : LGTResponse)

    fun onPostLoginGetTokenFailure(message: String)
}