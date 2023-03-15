package com.example.risingtest.src.main.add.tag

import com.example.risingtest.src.main.add.addmodels.AddRequest
import java.io.File

data class PostAddItem(
    val file : ArrayList<File>,
    val postProductReq : AddRequest
    )
