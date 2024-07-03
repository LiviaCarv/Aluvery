package com.project.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import com.project.aluvery.model.Product

class ProductDao {

    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun getProducts() = products.toList()

    fun saveProduct(product: Product) = products.add(product)
}