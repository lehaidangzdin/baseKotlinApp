package com.lhd.androidbase.data.apis

import com.lhd.androidbase.data.modelJson.ImageRes
import retrofit2.Response
import retrofit2.http.GET

interface FakeImagesApi {

    @GET("/product")
    suspend fun getAllImages(): Response<ImageRes>
}