package com.lhd.androidbase.data.modelJson

data class ResProductJson(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val ratingJson: RatingJson,
    val title: String
)