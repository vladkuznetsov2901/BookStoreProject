package com.example.bookstoreproject.data.models.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookstoreproject.domain.models.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getCartItemById(id: Int): Product?

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Delete
    suspend fun removeFromCart(product: Product)
}
