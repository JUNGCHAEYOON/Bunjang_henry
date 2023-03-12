package com.example.risingtest.src.main.home.itemdomain

import com.example.risingtest.src.main.home.itemdomain.models.ItemDomainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemDomainRetrofitInterface {
    @GET("/app/products/{id}" )
    fun getProducts(@Path("id") id : String) : Call<ItemDomainResponse>
}