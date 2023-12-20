package com.lhd.androidbase.data.apis

import com.lhd.androidbase.data.entity.params_entity.RegisterEntity
import com.lhd.androidbase.data.entity.reponse_entity.ResponseEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeImagesApi {

    @GET("/product")
    suspend fun getAllImages(): Response<ResponseEntity>

    @POST("/auth/register")
    suspend fun register(@Body registerEntity: RegisterEntity): Response<ResponseEntity>
}