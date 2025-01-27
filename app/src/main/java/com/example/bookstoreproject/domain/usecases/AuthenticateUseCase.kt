package com.example.bookstoreproject.domain.usecases

import com.example.bookstoreproject.domain.models.Product
import com.example.bookstoreproject.domain.models.User
import com.example.bookstoreproject.domain.repository.UserRepository
import javax.inject.Inject

class AuthenticateUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(username: String, password: String): User? {

        return userRepository.authenticate(username, password)

    }

}