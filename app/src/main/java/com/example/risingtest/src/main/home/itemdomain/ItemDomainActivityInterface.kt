package com.example.risingtest.src.main.home.itemdomain

import com.example.risingtest.src.main.home.itemdomain.models.ItemDomainResponse
import com.example.risingtest.src.main.home.itemdomain.storemodels.StoreInfoResponse

interface ItemDomainActivityInterface {
    // GetProducts
    fun onGetProductsSuccess(response : ItemDomainResponse)

    fun onGetProductsFailure(message: String)

    // GetStoreInfo
    fun onGetStoreInfoSuccess(response: StoreInfoResponse)

    fun onGetStoreInfoFailure(message : String)
}