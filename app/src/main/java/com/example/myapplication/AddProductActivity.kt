package com.example.myapplication

import ProductService
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.models.Product1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddProductActivity : AppCompatActivity() {
    private lateinit var spinnerProductType: Spinner
    private lateinit var editTextProductName: EditText
    private lateinit var editTextSellingPrice: EditText
    private lateinit var editTextTaxRate: EditText
    private lateinit var buttonAddProduct: Button

    private val productTypes = arrayOf("Electronic", "Product", "Toy") // Replace with your actual product types

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        spinnerProductType = findViewById(R.id.spinnerProductType)
        editTextProductName = findViewById(R.id.editTextProductName)
        editTextSellingPrice = findViewById(R.id.editTextSellingPrice)
        editTextTaxRate = findViewById(R.id.editTextTaxRate)
        buttonAddProduct = findViewById(R.id.buttonAddProduct)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, productTypes)
        spinnerProductType.adapter = adapter

        buttonAddProduct.setOnClickListener {
            addProduct()
        }
    }

    private fun addProduct() {
        // ...
        val productType = spinnerProductType.selectedItem.toString()
        val productName = editTextProductName.text.toString().trim()
        val sellingPrice = editTextSellingPrice.text.toString().toFloatOrNull()
        val taxRate = editTextTaxRate.text.toString().toFloatOrNull()

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://app.getswipe.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create the ProductService instance
        val productService = retrofit.create(ProductService::class.java)

        // Create the Product object
        val product = Product1(productType, productName, sellingPrice!!, taxRate!!)

        // Make the API call
        val call = productService.addProduct(product)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Display success message
                    Toast.makeText(this@AddProductActivity, "Product added successfully", Toast.LENGTH_SHORT).show()

                    // Reset the fields
                    spinnerProductType.setSelection(0)
                    editTextProductName.text.clear()
                    editTextSellingPrice.text.clear()
                    editTextTaxRate.text.clear()
                } else {
                    // Display error message
                    Toast.makeText(this@AddProductActivity, "Failed to add product", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Display error message
                Toast.makeText(this@AddProductActivity, "Failed to add product: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
