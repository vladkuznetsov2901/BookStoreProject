package com.example.bookstoreproject.domain.usecases

import com.example.bookstoreproject.domain.models.Product
import com.example.bookstoreproject.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): List<Product> {
        return productRepository.getAllProducts()
    }

}