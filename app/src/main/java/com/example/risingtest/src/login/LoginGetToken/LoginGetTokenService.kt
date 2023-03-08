package com.example.risingtest.src.login.LoginGetToken

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.login.LoginGetToken.models.LGTResponse
import com.example.risingtest.src.login.LoginGetToken.models.LGTRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginGetTokenService(val loginGetTokenInterface: LoginGetTokenInterface) {

    //LoginGetTokenRetrofitInterface
    //@POST("/app/users/create")
    //fun postLoginGetToken(@Body params: LGTResponse): Call<LGTLoginResponse>
    fun tryPostLoginGetToken(lgtRequest: LGTRequest){
        val loginGetTokenRetrofitInterface = ApplicationClass.sRetrofit.create(LoginGetTokenRetrofitInterface::class.java)
        loginGetTokenRetrofitInterface.postLoginGetToken(lgtRequest).enqueue(object : Callback<LGTResponse>{
            override fun onResponse(
                call: Call<LGTResponse>,
                response: Response<LGTResponse>
            ) {
                loginGetTokenInterface.onPostLoginGetTokenSuccess(response.body() as LGTResponse)
            }

            override fun onFailure(call: Call<LGTResponse>, t: Throwable) {
                loginGetTokenInterface.onPostLoginGetTokenFailure(t.message?:"통신오류")
            }

        })
    }
}