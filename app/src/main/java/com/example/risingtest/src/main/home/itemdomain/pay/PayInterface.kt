package com.example.risingtest.src.main.home.itemdomain.pay

import com.example.risingtest.src.main.home.itemdomain.pay.models.PayResponse

interface PayInterface {
    // onPostPay
    fun onPostPaySuccess(response : PayResponse)
    fun onPostPayFailure(message : String)
}