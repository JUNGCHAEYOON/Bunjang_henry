package com.example.risingtest.src.main.add.tag.models

data class TagResponse(
    val code: Int?,
    val isSuccess: Boolean?,
    val message: String?,
    val result: List<Result?>?
)