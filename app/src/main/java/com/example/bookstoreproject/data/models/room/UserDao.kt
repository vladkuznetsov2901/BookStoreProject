package com.example.bookstoreproject.data.models.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookstoreproject.domain.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun authenticate(username: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)
}