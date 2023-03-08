package com.example.risingtest.src.main.home

import com.example.risingtest.src.main.home.banner.HomeAdViewResponse

interface HomeFragmentInterface {
    fun onGetBannersSuccess(response : HomeAdViewResponse)

    fun onGetBannersFailure(message: String)
}