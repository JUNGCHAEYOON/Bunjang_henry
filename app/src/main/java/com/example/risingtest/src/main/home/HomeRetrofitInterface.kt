package com.example.risingtest.src.main.home

import com.example.risingtest.src.main.home.bannermodels.HomeAdViewResponse
import com.example.risingtest.src.main.home.listmodels.HomeListResponse
import retrofit2.Call
import retrofit2.http.GET

interface HomeRetrofitInterface {
    @GET("/app/home/banners")
    fun getBanners() : Call<HomeAdViewResponse>

    @GET("/app/products/main")
    fun getList() : Call<HomeListResponse>

}