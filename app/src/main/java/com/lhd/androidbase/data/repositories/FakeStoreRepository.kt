package com.lhd.androidbase.data.repositories

import com.lhd.androidbase.base.network.NetworkResult
import com.lhd.androidbase.data.models.Product
import com.lhd.androidbase.data.services.FakeStoreRemoteService
import com.lhd.androidbase.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeStoreRepository @Inject constructor(
    private val fakeStoreRemoteService: FakeStoreRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllProduct() = withContext(dispatcher) {
        when (val res = fakeStoreRemoteService.getAllProduct()) {
            is NetworkResult.Success -> {
                res.data.map { it.toProduct() }
            }
            is NetworkResult.Error -> {
                throw res.exception
            }
        }
    }

    suspend fun getAProduct(id: String) = withContext(dispatcher) {
        when (val res = fakeStoreRemoteService.getAProduct(id)) {
            is NetworkResult.Success -> {
                res.data
            }
            is NetworkResult.Error -> {
                throw res.exception
            }
        }
    }

    suspend fun addProduct(product: Product) = withContext(dispatcher) {
        when (val res = fakeStoreRemoteService.addProduct(product)) {
            is NetworkResult.Success -> {
                res.data
            }
            is NetworkResult.Error -> {
                throw res.exception
            }
        }
    }

    suspend fun deleteProduct(id: String) = withContext(dispatcher) {
        when (val res = fakeStoreRemoteService.deleteProduct(id)) {
            is NetworkResult.Success -> {
                res.data
            }
            is NetworkResult.Error -> {
                throw res.exception
            }
        }
    }

}