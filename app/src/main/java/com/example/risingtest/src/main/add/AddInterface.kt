package com.example.risingtest.src.main.add

import com.example.risingtest.src.main.add.addmodels.AddResponse

interface AddInterface {
    fun onPostAddSuccess(response : AddResponse)
    fun onPostAddFailure(message : String)
}