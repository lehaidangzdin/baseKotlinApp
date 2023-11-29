package com.lhd.androidbase.data.services

import com.lhd.androidbase.base.network.BaseRemoteService
import com.lhd.androidbase.base.network.NetworkResult
import com.lhd.androidbase.data.apis.FakeImagesApi
import com.lhd.androidbase.data.modelJson.ImageRes
import javax.inject.Inject

class FakeImageRemoteService @Inject constructor(private val fakeImageApi: FakeImagesApi) :
    BaseRemoteService() {

    suspend fun getAllProduct(): NetworkResult<ImageRes> {
        return callApi { fakeImageApi.getAllImages() }
    }


}