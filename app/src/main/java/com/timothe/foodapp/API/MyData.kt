package com.timothe.foodapp.API

data class MyData (
    val status: Int,
    val code: String,
    val product: Product

)
data class Product(val product_name: String, val image_small_url: String,
    val ingredients_tags: List<String>
)