package com.example.risingtest.src.main.add.category

import android.app.Application
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.add.category.models.CG1Response
import com.example.risingtest.src.main.home.HomeRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import retrofit2.http.GET

class CGService (val cGInterface: CGInterface) {
    //CGRetrofitInterface
    //@GET("/app/categories/major")
    //fun getCategory() : Call<CG1Response>
    fun tryGetCategory(){
        val cGRetrofitInterface = ApplicationClass.sRetrofit.create(CGRetrofitInterface::class.java)
        cGRetrofitInterface.getCategory().enqueue(object : Callback<CG1Response>{
            override fun onResponse(call: Call<CG1Response>, response: Response<CG1Response>) {
                cGInterface.onGetCategorySuccess(response.body() as CG1Response)
            }

            override fun onFailure(call: Call<CG1Response>, t: Throwable) {
                cGInterface.onGetCategoryFailure(t.message?: "통신 오류")
            }

        })
    }

    fun tryGetMiddle(categoryId : String){
        val cGRetrofitInterface = ApplicationClass.sRetrofit.create(CGRetrofitInterface::class.java)
        cGRetrofitInterface.getMiddle(categoryId).enqueue(object : Callback<CG1Response>{
            override fun onResponse(call: Call<CG1Response>, response: Response<CG1Response>) {
                cGInterface.onGetMiddleSuccess(response.body() as CG1Response)
            }

            override fun onFailure(call: Call<CG1Response>, t: Throwable) {
                cGInterface.onGetMiddleFailure(t.message?: "통신오류")
            }

        })
    }

    fun tryGetLast(categoryId : String){
        val cGRetrofitInterface = ApplicationClass.sRetrofit.create(CGRetrofitInterface::class.java)
        cGRetrofitInterface.getLast(categoryId).enqueue(object : Callback<CG1Response>{
            override fun onResponse(call: Call<CG1Response>, response: Response<CG1Response>) {
                cGInterface.onGetLastSuccess(response.body() as CG1Response)
            }

            override fun onFailure(call: Call<CG1Response>, t: Throwable) {
                cGInterface.onGetLastFailure(t.message?: "통신오류")
            }

        })
    }
}