package com.lhd.androidbase.data.models
import java.io.Serializable

data class Product(
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String
): Serializable




