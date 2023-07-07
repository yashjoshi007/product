package com.example.myapp.models

import com.google.gson.annotations.SerializedName
import java.io.File

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
    @SerializedName("product_type")
    val productType: String,

    @SerializedName("product_name")
    val productName: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("tax")
    val tax: String,

    val image: String? = null
)







