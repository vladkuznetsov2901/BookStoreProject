package com.example.bookstoreproject.domain.models

data class Book(
    val id_book: Int,
    val book_name: String,
    val price: Int,
    val id_genre: Int,
    val id_edition: Int,
    val id_author: Int,
    val year: Int,
    val imagepath: String,
    var isInCart: Boolean = false
)
