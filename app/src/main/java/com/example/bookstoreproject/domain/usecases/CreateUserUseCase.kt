package com.example.bookstoreproject.domain.usecases

import com.example.bookstoreproject.domain.models.User
import com.example.bookstoreproject.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User) {

        return userRepository.createUser(user)

    }

}