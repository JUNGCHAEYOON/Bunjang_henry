package com.example.risingtest.src.main.home.listRecycler

data class RecyclerItem (
    var categorytitle : String?,
    var id : Int?,
    var userId : Int?,
    var price : Int? = 0,
    var checkMyProduct : Boolean? = false,
    var safeCare : Boolean? = false,
    var safePay : Boolean? = false,
    var title : String?,
    var url : String?,
)