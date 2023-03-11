package com.example.risingtest.src.main.home

import com.example.risingtest.src.main.home.bannermodels.HomeAdViewResponse
import com.example.risingtest.src.main.home.listmodels.HomeListResponse

interface HomeFragmentInterface {
    //GetBanners
    fun onGetBannersSuccess(response : HomeAdViewResponse)

    fun onGetBannersFailure(message: String)

    //GetList
    fun onGetListSuccess(response : HomeListResponse)

    fun onGetListsFailure(message: String)
}