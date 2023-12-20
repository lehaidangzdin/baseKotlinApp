package com.lhd.androidbase.data.repositories

import com.lhd.androidbase.base.network.NetworkResult
import com.lhd.androidbase.data.entity.params_entity.RegisterEntity
import com.lhd.androidbase.data.services.FakeImageRemoteService
import com.lhd.androidbase.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeImageRepository @Inject constructor(
    private val fakeImageRemoteService: FakeImageRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllImage() = withContext(dispatcher) {
        when (val res = fakeImageRemoteService.getAllProduct()) {
            is NetworkResult.Success -> {
                res.data
            }

            is NetworkResult.Error -> {
                throw res.exception
            }
        }
    }
    suspend fun register(registerEntity: RegisterEntity) = withContext(dispatcher) {
        when (val res = fakeImageRemoteService.register(registerEntity)) {
            is NetworkResult.Success -> {
                res.data
            }

            is NetworkResult.Error -> {
                throw res.exception
            }
        }
    }

}