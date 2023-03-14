package com.example.risingtest.src.main.add.tag

import com.example.risingtest.src.main.add.tag.models.TagResponse

interface TagInterface {
    //GetTag
    fun onGetTagSuccess(response : TagResponse)
    fun onGetTagFailure(message:String)
}