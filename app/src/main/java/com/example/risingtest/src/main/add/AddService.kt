package com.example.risingtest.src.main.add

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.add.addmodels.AddRequest
import com.example.risingtest.src.main.add.addmodels.AddResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    fun tryPostAdd(file : ArrayList<File>, postProductReq : AddRequest){
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
}