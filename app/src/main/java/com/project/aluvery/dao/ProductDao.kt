package com.project.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import com.project.aluvery.model.Product
import com.project.aluvery.sample.sampleProducts

class ProductDao {

    companion object {
        private val products = mutableStateListOf<Product>(*sampleProducts.toTypedArray())
    }

    fun getProducts() = products.toList()

    fun saveProduct(product: Product) = products.add(product)
}