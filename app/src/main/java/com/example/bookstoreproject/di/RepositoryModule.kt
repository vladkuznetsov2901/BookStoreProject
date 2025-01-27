package com.example.bookstoreproject.di

import com.example.bookstoreproject.data.models.room.ProductDao
import com.example.bookstoreproject.data.models.room.UserDao
import com.example.bookstoreproject.data.repository.ProductRepositoryImpl
import com.example.bookstoreproject.data.repository.UserRepositoryImpl
import com.example.bookstoreproject.domain.repository.ProductRepository
import com.example.bookstoreproject.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepositoryImpl(userDao)
    }

    @Provides
    fun provideProductRepository(productDao: ProductDao): ProductRepository {
        return ProductRepositoryImpl(productDao)
    }
}
