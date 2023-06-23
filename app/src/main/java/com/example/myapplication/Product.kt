package com.example.myapp.models

data class Product(
    val product_name: String,
    val product_type: String,
    val price: Double,
    val tax: Double,
    val image: String?


)
{
    val productName: String
        get() = product_name

    val productType: String
        get() = product_type
}

data class Product1(
    val type: String,
    val name: String,
    val sellingPrice: Float,
    val taxRate: Float
)

