package com.lhd.androidbase.data.apis

import com.lhd.androidbase.data.modelJson.ResAddProductJson
import com.lhd.androidbase.data.modelJson.ResProductJson
import com.lhd.androidbase.data.models.Product
import retrofit2.Response
import retrofit2.http.*

interface FakeStoreApi {

    @GET("products")
    suspend fun getAllProduct(): Response<List<ResProductJson>>


    @GET("products/{id}")
    suspend fun getAProduct(@Path("id") id: String): Response<ResProductJson>


    @POST("products")
    suspend fun addProduct(@Body product: Product): Response<ResAddProductJson>


    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: String): Response<ResProductJson>
}