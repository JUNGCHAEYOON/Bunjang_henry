package com.example.risingtest.src.main.home

import com.example.risingtest.src.main.home.banner.HomeAdViewResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeRetrofitInterface {
    @GET("/app/home/banners")
    fun getBanners(
        @Header("X-ACCESS-TOKEN") token: String?,
    ) : Call<HomeAdViewResponse>

}