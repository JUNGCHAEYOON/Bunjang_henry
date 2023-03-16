package com.example.risingtest.src.main.add

import com.example.risingtest.src.main.add.add2models.Add2Response
import com.example.risingtest.src.main.add.addmodels.AddResponse

interface AddInterface {
    // PostAdd
    fun onPostAddSuccess(response : AddResponse)
    fun onPostAddFailure(message : String)

    // PostAdd2
    fun onPostAdd2Success(response : Add2Response)
    fun onPostAdd2Failure(message : String)
}