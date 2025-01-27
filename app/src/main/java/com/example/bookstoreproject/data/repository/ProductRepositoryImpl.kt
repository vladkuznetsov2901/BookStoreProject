package com.example.bookstoreproject.data.repository

import com.example.bookstoreproject.data.models.room.ProductDao
import com.example.bookstoreproject.domain.models.Product
import com.example.bookstoreproject.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productDao: ProductDao) :
    ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    override suspend fun addProduct(product: Product) {
        productDao.insert(product)
    }

}