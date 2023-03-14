package com.example.risingtest.src.main.add.tag

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.add.tag.models.TagResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

class TagService(val tagInterface : TagInterface) {
    //TagRetrofitInterface
    //@GET("/app/tags?query=")
    //fun GetTag(@Query("param") param1 : String) : Call<TagResponse>
    fun tryGetTag(params : String){
        val TagRetrofitInterface = ApplicationClass.sRetrofit.create(TagRetrofitInterface::class.java)
        TagRetrofitInterface.getTag(params).enqueue(object : Callback<TagResponse>{
            override fun onResponse(call: Call<TagResponse>, response: Response<TagResponse>) {
                tagInterface.onGetTagSuccess(response.body() as TagResponse)
            }

            override fun onFailure(call: Call<TagResponse>, t: Throwable) {
                tagInterface.onGetTagFailure(t.message ?: "통신 오류")
            }

        })
    }
}