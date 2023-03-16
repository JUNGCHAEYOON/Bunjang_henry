package com.example.risingtest.src.main.home.itemdomain.pay

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.home.itemdomain.pay.models.PayRequest
import com.example.risingtest.src.main.home.itemdomain.pay.models.PayResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

class PayService (val payInterface: PayInterface){

    //PayRetrofitInterface
//    @POST("/app/payments/:productId")
//    fun postPay(
//        @Body param : PayRequest
//    ) : Call<PayResponse>
    fun tryPostPay(param : PayRequest, productId : String){
        val payRetrofitInterface = ApplicationClass.sRetrofit.create(PayRetrofitInterface::class.java)
        payRetrofitInterface.postPay(param, productId).enqueue(object : Callback<PayResponse>{
            override fun onResponse(call: Call<PayResponse>, response: Response<PayResponse>) {
                payInterface.onPostPaySuccess(response.body() as PayResponse)
            }

            override fun onFailure(call: Call<PayResponse>, t: Throwable) {
                payInterface.onPostPayFailure(t.message ?: "통신 오류")
            }

        })
    }
}