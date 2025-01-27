package com.example.bookstoreproject.domain.repository

import com.example.bookstoreproject.domain.models.User

interface UserRepository {

    suspend fun authenticate(username: String, password: String): User?
    suspend fun createUser(user: User)
}