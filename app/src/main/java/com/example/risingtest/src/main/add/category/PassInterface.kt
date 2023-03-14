package com.example.risingtest.src.main.add.category

interface PassInterface {
    //대->중
    fun onPassCategoryId(categoryId : Int)
    fun onPassCategoryTitle(title : String)

    //중->소
    fun onPasslastId(categoryId : Int)
    fun onPasslastTitle(title : String)
}