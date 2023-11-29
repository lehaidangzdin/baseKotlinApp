package com.lhd.androidbase.data.services

//class FirebaseRemoteService @Inject constructor(firebaseFirestore: FirebaseFirestore) {
//
//    private val databaseRef = firebaseFirestore.collection("products")
//
//    suspend fun getAll(): List<Product> {
//        return try {
//            val lsData = mutableListOf<Product>()
//            val snapshot = databaseRef.get().await()
//            snapshot.map { document ->
//                val data = document.toObject(Product::class.java)
//                data.let { lsData.add(data) }
//            }
//            lsData
//        } catch (e: Exception) {
//            throw Exception(e)
//        }
//    }
//}