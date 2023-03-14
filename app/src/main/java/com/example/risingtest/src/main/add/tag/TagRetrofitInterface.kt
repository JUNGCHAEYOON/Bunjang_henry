package com.example.risingtest.src.main.add.tag

import com.example.risingtest.src.main.add.tag.models.TagResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TagRetrofitInterface {
    @GET("/app/tags")
    fun getTag(@Query("query") params : String) : Call<TagResponse>
}