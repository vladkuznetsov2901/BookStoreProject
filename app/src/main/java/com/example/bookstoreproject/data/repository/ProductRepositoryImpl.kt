package com.example.bookstoreproject.data.repository

import com.example.bookstoreproject.data.api.ProductsApi.Companion.retrofit
import com.example.bookstoreproject.data.models.room.ProductDao
import com.example.bookstoreproject.domain.models.Book
import com.example.bookstoreproject.domain.models.Product
import com.example.bookstoreproject.domain.repository.ProductRepository
import retrofit2.Call
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao) :
    ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    override suspend fun getAllProductsFromAPI(): Call<List<Book>> {
        return retrofit.loadProducts()
    }

    override suspend fun addProduct(product: Product) {
        productDao.insert(product)
    }

}