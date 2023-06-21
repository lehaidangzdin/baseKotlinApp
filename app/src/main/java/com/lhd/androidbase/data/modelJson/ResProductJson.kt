package com.lhd.androidbase.data.modelJson

import com.lhd.androidbase.data.models.Product

data class ResProductJson(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingJson
) {
    fun toProduct(): Product {
        return Product(
            id = id,
            category = category,
            description = description,
            image = image,
            price = price,
            title = title
        )
    }
}