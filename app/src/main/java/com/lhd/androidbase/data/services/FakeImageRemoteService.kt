package com.lhd.androidbase.data.services

import com.lhd.androidbase.base.network.BaseRemoteService
import com.lhd.androidbase.base.network.NetworkResult
import com.lhd.androidbase.data.apis.FakeImagesApi
import com.lhd.androidbase.data.entity.params_entity.RegisterEntity
import com.lhd.androidbase.data.entity.reponse_entity.ResponseEntity
import javax.inject.Inject

class FakeImageRemoteService @Inject constructor(private val fakeImageApi: FakeImagesApi) :
    BaseRemoteService() {

    suspend fun getAllProduct(): NetworkResult<ResponseEntity> {
        return callApi { fakeImageApi.getAllImages() }
    }
    suspend fun register(registerEntity: RegisterEntity): NetworkResult<ResponseEntity> {
        return callApi { fakeImageApi.register(registerEntity) }
    }


}