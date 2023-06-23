package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.myapp.models.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var productList: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        private val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        private val textViewProductType: TextView = itemView.findViewById(R.id.textViewProductType)
        private val textViewProductPrice: TextView = itemView.findViewById(R.id.textViewProductPrice)
        private val textViewProductTax: TextView = itemView.findViewById(R.id.textViewProductTax)

        fun bind(product: Product) {
            // Display product information in the views
            textViewProductName.text = product.productName
            textViewProductType.text = product.productType
            textViewProductPrice.text = product.price.toString()
            textViewProductTax.text = product.tax.toString()

            // Load product image from URL using Glide library
            Glide.with(itemView)
                .load(product.image)
                .placeholder(R.drawable.placeholder_image)
                .into(imageViewProduct)
        }
    }
}
