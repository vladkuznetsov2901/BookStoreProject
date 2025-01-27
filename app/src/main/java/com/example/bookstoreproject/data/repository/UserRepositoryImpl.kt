package com.example.bookstoreproject.data.repository

import com.example.bookstoreproject.data.models.room.UserDao
import com.example.bookstoreproject.domain.models.User
import com.example.bookstoreproject.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun authenticate(username: String, password: String): User? {
        return userDao.authenticate(username, password)
    }

    override suspend fun createUser(user: User) {
        userDao.insert(user)
    }
}