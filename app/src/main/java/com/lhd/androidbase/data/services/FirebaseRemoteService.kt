package com.lhd.androidbase.data.services

import com.google.firebase.firestore.FirebaseFirestore
import com.lhd.androidbase.data.models.Product
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRemoteService @Inject constructor(private val firebaseFirestore: FirebaseFirestore) {

    private val databaseRef = firebaseFirestore.collection("products")

    suspend fun getAll(): List<Product> {
        return try {
            val lsData = mutableListOf<Product>()
            val snapshot = databaseRef.get().await()
            snapshot.map { document ->
                val data = document.toObject(Product::class.java)
                data.let { lsData.add(data) }
            }
            lsData
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}