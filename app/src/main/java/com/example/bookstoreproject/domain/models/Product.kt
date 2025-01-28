package com.example.bookstoreproject.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey()
    val id: Int = 0,
    val name: String,
    val price: Int,
    val year: Int,
    val imagepath: String
)

