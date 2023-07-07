package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add code to navigate to the com.example.myapplication.com.example.myapplication.ProductListActivity when needed
        // For example, you can add a button in the activity_main.xml file
        val productListButton: Button = findViewById(R.id.productListButton)
        productListButton.setOnClickListener {
            startActivity(Intent(this, ProductListActivity::class.java))
        }




    }
}