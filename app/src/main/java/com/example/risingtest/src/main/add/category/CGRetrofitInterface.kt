package com.example.risingtest.src.main.add.category

import com.example.risingtest.src.main.add.category.models.CG1Response
import com.example.risingtest.src.main.home.bannermodels.HomeAdViewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CGRetrofitInterface {
    @GET("/app/categories/major")
    fun getCategory() : Call<CG1Response>

    @GET("/app/categories/middle/{categoryId}")
    fun getMiddle(@Path("categoryId") categoryId : String) : Call<CG1Response>
}