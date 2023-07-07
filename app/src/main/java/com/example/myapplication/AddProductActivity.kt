package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException

class AddProductActivity : AppCompatActivity() {
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addproductactivity)

        val buttonAddProduct = findViewById<Button>(R.id.buttonAddProduct)
        val editTextProductName = findViewById<EditText>(R.id.editTextProductName)
        val editTextProductType = findViewById<EditText>(R.id.editTextProductType)
        val editTextPrice = findViewById<EditText>(R.id.editTextPrice)
        val editTextTax = findViewById<EditText>(R.id.editTextTax)

        buttonAddProduct.setOnClickListener {
            val productName = editTextProductName.text.toString()
            val productType = editTextProductType.text.toString()
            val price = editTextPrice.text.toString()
            val tax = editTextTax.text.toString()

            addProduct(productName, productType, price, tax)
        }
    }


    private fun addProduct(
        productName: String,
        productType: String,
        price: String,
        tax: String
    ) {
        val url = "https://app.getswipe.in/api/public/add"

        val requestBody = FormBody.Builder()
            .add("product_name", productName)
            .add("product_type", productType)
            .add("price", price)
            .add("tax", tax)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        try {
            val response = runBlocking {
                withContext(Dispatchers.IO) {
                    client.newCall(request).execute()
                }
            }

            // Handle the response as needed
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                showToast("Product added successfully. Response: $responseBody")
            } else {
                showToast("Failed to add product. Response code: ${response.code}")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            showToast("Error: ${e.message}")
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

