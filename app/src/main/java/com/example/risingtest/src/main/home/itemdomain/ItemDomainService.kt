package com.example.risingtest.src.main.home.itemdomain

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.home.itemdomain.models.ItemDomainResponse
import com.example.risingtest.src.main.home.itemdomain.storemodels.StoreInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class ItemDomainService (val itemDomainActivityInterface : ItemDomainActivityInterface) {
    //ItemDomainRetrofitInterface
    //@GET("/app/products/" )
    //fun getProducts(@Path("id") id : String) : Call<ItemDomainResponse>
    fun tryGetProducts(id : String){
        val itemDomainRetrofitInterface = ApplicationClass.sRetrofit.create(ItemDomainRetrofitInterface::class.java)
        itemDomainRetrofitInterface.getProducts(id).enqueue(object : Callback<ItemDomainResponse>{
            override fun onResponse(
                call: Call<ItemDomainResponse>,
                response: Response<ItemDomainResponse>
            ) {
                //ItemDomainActivityInterface
                //fun onGetProductsSuccess(response : ItemDomainResponse)
                itemDomainActivityInterface.onGetProductsSuccess(response.body() as ItemDomainResponse)
            }

            override fun onFailure(call: Call<ItemDomainResponse>, t: Throwable) {
                //ItemDomainActivityInterface
                //fun onGetProductsFailure(message: String)
                itemDomainActivityInterface.onGetProductsFailure(t.message ?: "통신 오류")
            }

        })
    }

    //ItemDomainRetrofitInterface
    //@GET("/app/users/{id}")
    //fun getStoreInfo(@Path("id") id : String) : Call<StoreInfoResponse>
    fun tryGetStoreInfo(userId : String){
        val itemDomainRetrofitInterface = ApplicationClass.sRetrofit.create(ItemDomainRetrofitInterface::class.java)
        itemDomainRetrofitInterface.getStoreInfo(userId).enqueue(object : Callback<StoreInfoResponse>{
            override fun onResponse(
                call: Call<StoreInfoResponse>,
                response: Response<StoreInfoResponse>
            ) {
                itemDomainActivityInterface.onGetStoreInfoSuccess(response.body() as StoreInfoResponse)
            }

            override fun onFailure(call: Call<StoreInfoResponse>, t: Throwable) {
                itemDomainActivityInterface.onGetStoreInfoFailure(t.message ?: "통신 오류")
            }

        })
    }
}