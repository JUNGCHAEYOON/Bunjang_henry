package com.example.risingtest.src.main.home.itemdomain

import com.example.risingtest.src.main.home.itemdomain.models.ItemDomainResponse

interface ItemDomainActivityInterface {
    // GetProducts
    fun onGetProductsSuccess(response : ItemDomainResponse)

    fun onGetProductsFailure(message: String)
}