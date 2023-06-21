package com.lhd.androidbase.data.repositories

import com.lhd.androidbase.data.models.Product
import com.lhd.androidbase.data.services.FirebaseRemoteService
import com.lhd.androidbase.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher

import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    private val firebaseRemoteService: FirebaseRemoteService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAll(): List<Product> {
        return firebaseRemoteService.getAll()
    }

    suspend fun addUser(product: Product) {

    }

}