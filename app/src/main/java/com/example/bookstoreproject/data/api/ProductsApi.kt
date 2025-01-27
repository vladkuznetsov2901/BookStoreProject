package com.example.bookstoreproject.data.api

import com.example.bookstoreproject.domain.models.Book
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ProductsApi {

    @GET("/products")
    fun loadProducts(): Call<List<Book>>

    companion object {

        val retrofit by lazy {
            Retrofit
                .Builder()
                .baseUrl("https://bookshopapi.wiremockapi.cloud/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<ProductsApi>()
        }
    }


}