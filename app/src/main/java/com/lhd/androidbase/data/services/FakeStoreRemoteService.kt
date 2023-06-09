package com.lhd.androidbase.data.services

import com.lhd.androidbase.base.network.BaseRemoteService
import com.lhd.androidbase.base.network.NetworkResult
import com.lhd.androidbase.data.apis.FakeStoreApi
import com.lhd.androidbase.data.modelJson.ResAddProductJson
import com.lhd.androidbase.data.modelJson.ResProductJson
import com.lhd.androidbase.data.models.Product
import javax.inject.Inject

class FakeStoreRemoteService @Inject constructor(private val fakeStoreApi: FakeStoreApi) :
    BaseRemoteService() {

    suspend fun getAllProduct(): NetworkResult<List<ResProductJson>> {
        return callApi { fakeStoreApi.getAllProduct() }
    }

    suspend fun getAProduct(id: String): NetworkResult<ResProductJson> {
        return callApi { fakeStoreApi.getAProduct(id) }
    }

    suspend fun addProduct(product: Product): NetworkResult<ResAddProductJson> {
        return callApi { fakeStoreApi.addProduct(product) }
    }

    suspend fun deleteProduct(id: String): NetworkResult<ResProductJson> {
        return callApi { fakeStoreApi.deleteProduct(id) }
    }


}