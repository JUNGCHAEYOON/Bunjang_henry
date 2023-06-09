package com.example.risingtest.src.main.home

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.home.bannermodels.HomeAdViewResponse
import com.example.risingtest.src.main.home.listmodels.HomeListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class HomeService (val homeFragmentInterface: HomeFragmentInterface){

    //HomeRetrofitInterface
    //@GET("/app/banners")
    //fun getBanners() : Call<HomeAdViewResponse>
    fun tryGetBanners(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getBanners().enqueue(object : Callback<HomeAdViewResponse>{
            override fun onResponse(
                call: Call<HomeAdViewResponse>,
                response: Response<HomeAdViewResponse>
            ) {
                //HomeFragmentInterface
                //onGetBannersSuccess
                homeFragmentInterface.onGetBannersSuccess(response.body() as HomeAdViewResponse)
            }

            override fun onFailure(call: Call<HomeAdViewResponse>, t: Throwable) {
                //HomeFragmentInterface
                //onGetBannersFailure
                homeFragmentInterface.onGetBannersFailure(t.message ?: "통신 오류")
            }

        })
    }

    //HomeRetrofitInterface
    //@GET("/app/products/main")
    //fun getList() : Call<HomeListResponse>
    fun tryGetList(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getList().enqueue(object : Callback<HomeListResponse>{
            override fun onResponse(
                call: Call<HomeListResponse>,
                response: Response<HomeListResponse>
            ) {
                homeFragmentInterface.onGetListSuccess(response.body() as HomeListResponse)
            }

            override fun onFailure(call: Call<HomeListResponse>, t: Throwable) {
                homeFragmentInterface.onGetBannersFailure(t.message ?: "통신 오류")
            }

        })
    }
}