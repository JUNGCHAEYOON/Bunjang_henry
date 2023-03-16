package com.example.risingtest.src.main.home.itemdomain.pay

import com.example.risingtest.src.main.home.itemdomain.pay.models.PayRequest
import com.example.risingtest.src.main.home.itemdomain.pay.models.PayResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PayRetrofitInterface {
    @POST("/app/payments/{productId}")
    fun postPay(
        @Body param : PayRequest,
        @Path("productId") productId : String
    ) : Call<PayResponse>
}