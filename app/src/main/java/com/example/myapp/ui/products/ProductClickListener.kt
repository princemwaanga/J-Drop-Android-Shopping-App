package com.example.myapp.ui.products

interface ProductClickListener {
    fun onProductClicked(product: Product)
    fun onAddToCartClicked(product: Product)
}