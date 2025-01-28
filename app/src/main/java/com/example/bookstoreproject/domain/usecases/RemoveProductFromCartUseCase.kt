package com.example.bookstoreproject.domain.usecases

import com.example.bookstoreproject.domain.models.Product
import com.example.bookstoreproject.domain.repository.ProductRepository
import javax.inject.Inject

class RemoveProductFromCartUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(product: Product) {
        productRepository.removeProduct(product)
    }

}