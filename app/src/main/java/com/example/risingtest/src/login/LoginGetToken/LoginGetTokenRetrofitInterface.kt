package com.example.risingtest.src.login.LoginGetToken

import com.example.risingtest.src.login.LoginGetToken.models.LGTResponse
import com.example.risingtest.src.login.LoginGetToken.models.LGTRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginGetTokenRetrofitInterface {

    @POST("/app/users")
    fun postLoginGetToken(@Body params: LGTRequest): Call<LGTResponse>
}