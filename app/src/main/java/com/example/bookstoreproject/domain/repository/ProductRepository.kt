package com.example.bookstoreproject.domain.repository

import com.example.bookstoreproject.domain.models.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun addProduct(product: Product)

}
