package com.example.bookstoreproject.domain.repository

import com.example.bookstoreproject.domain.models.Book
import com.example.bookstoreproject.domain.models.Product
import retrofit2.Call

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getAllProductsFromAPI(): Call<List<Book>>
    suspend fun addProduct(product: Product)
    suspend fun getProductByID(id: Int): Product?
    suspend fun removeProduct(product: Product)

}
