package com.example.bookstoreproject.data.models.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookstoreproject.domain.models.Product
import com.example.bookstoreproject.domain.models.User

@Database(
    entities = [User::class, Product::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao

}