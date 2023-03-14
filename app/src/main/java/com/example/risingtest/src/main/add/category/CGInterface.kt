package com.example.risingtest.src.main.add.category

import com.example.risingtest.src.main.add.category.models.CG1Response

interface CGInterface {

    //대분류
    fun onGetCategorySuccess(response : CG1Response)
    fun onGetCategoryFailure(message : String)

    //중분류
    fun onGetMiddleSuccess(response : CG1Response)
    fun onGetMiddleFailure(message : String)

    //소분류
    fun onGetLastSuccess(response : CG1Response)
    fun onGetLastFailure(message : String)
}