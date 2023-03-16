package com.example.risingtest.src.main.add

import android.provider.Telephony.Mms.Addr
import com.example.risingtest.src.main.add.addmodels.AddRequest
import com.example.risingtest.src.main.add.addmodels.AddResponse
import com.example.risingtest.src.main.add.tag.PostAddItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface AddRetrofitInterface {

    @Multipart
    @POST("/app/products")
    fun postAdd(
//        @Part ("file") file : ArrayList<File>,
//        @Part ("postProductReq")postProductReq : AddRequest

        @Part file : ArrayList<MultipartBody.Part>,
//        @Part ("postProductReq") postProductReq : AddRequest
        @Part ("postProductReq") postProductReq : AddRequest
    ) : Call<AddResponse>
}