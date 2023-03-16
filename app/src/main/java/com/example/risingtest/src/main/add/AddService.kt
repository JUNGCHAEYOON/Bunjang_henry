package com.example.risingtest.src.main.add

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.add.add2models.Add2Request
import com.example.risingtest.src.main.add.add2models.Add2Response
import com.example.risingtest.src.main.add.addmodels.AddRequest
import com.example.risingtest.src.main.add.addmodels.AddResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.File

class AddService (val addInterface: AddInterface) {
    //AddRetrofitInterface
//    @POST("/app/products")
//    fun postAdd(
//        @Body file : ArrayList<File>,,
//        @Body postProductReq : AddRequest
//    ) : Call<AddResponse>
//    fun tryPostAdd(file : ArrayList<File>, postProductReq : AddRequest){
    fun tryPostAdd(file : ArrayList<MultipartBody.Part>, postProductReq : AddRequest){
        val addRetrofitInterface = ApplicationClass.sRetrofit.create(AddRetrofitInterface::class.java)
        addRetrofitInterface.postAdd(file, postProductReq).enqueue(object : Callback<AddResponse>{
            override fun onResponse(call: Call<AddResponse>, response: Response<AddResponse>) {
                addInterface.onPostAddSuccess(response.body() as AddResponse)
            }

            override fun onFailure(call: Call<AddResponse>, t: Throwable) {
                addInterface.onPostAddFailure(t.message ?: "통신 오류")
            }
        })
    }

    //AddRetrofitInterface
    //@POST("/app/products/enrollment")
    //fun postAdd2(@Body postProductReq : Add2Request) : Call<Add2Response>
    fun tryPostAdd2(postProductReq: Add2Request){
        val addRetrofitInterface = ApplicationClass.sRetrofit.create(AddRetrofitInterface::class.java)
        addRetrofitInterface.postAdd2(postProductReq).enqueue(object : Callback<Add2Response>{
            override fun onResponse(call: Call<Add2Response>, response: Response<Add2Response>) {
                addInterface.onPostAdd2Success(response.body() as Add2Response)
            }

            override fun onFailure(call: Call<Add2Response>, t: Throwable) {
                addInterface.onPostAdd2Failure(t.message ?: "통신 오류")
            }
        })
    }
}