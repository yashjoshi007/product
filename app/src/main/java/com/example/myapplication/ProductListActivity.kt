package com.example.myapplication

import Retrofitclient
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.models.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductListActivity : AppCompatActivity() {
    private lateinit var editTextSearch: EditText
    private lateinit var buttonAddProduct: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    private var productList: List<Product> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        editTextSearch = findViewById(R.id.editTextSearch)
        buttonAddProduct = findViewById(R.id.buttonAddProduct)
        progressBar = findViewById(R.id.progressBar)
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts)

        productAdapter = ProductAdapter()
        recyclerViewProducts.apply {
            layoutManager = LinearLayoutManager(this@ProductListActivity)
            adapter = productAdapter
        }

        getProductData()

        buttonAddProduct.setOnClickListener {
            // Navigate to the Add Product screen
            startActivity(Intent(this, AddProductActivity::class.java))
        }
    }

    private fun getProductData() {
        progressBar.visibility = View.VISIBLE

        val service = Retrofitclient.productApiService
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getProducts().execute() // Execute the API request synchronously

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            productList = responseBody
                            productAdapter.updateData(productList)
                        }
                    } else {
                        Toast.makeText(
                            this@ProductListActivity,
                            "Failed to fetch product data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ProductListActivity,
                        "Error: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

}
