package com.example.bookstoreproject.domain.usecases

import com.example.bookstoreproject.domain.models.Book
import com.example.bookstoreproject.domain.models.Product
import com.example.bookstoreproject.domain.repository.ProductRepository
import retrofit2.Call
import javax.inject.Inject

class GetAllProductsFromAPIUseCase @Inject constructor(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): Call<List<Book>> {
        return productRepository.getAllProductsFromAPI()
    }

}