package com.example.bookstoreproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoreproject.domain.models.Book
import com.example.bookstoreproject.domain.models.Product
import com.example.bookstoreproject.domain.models.User
import com.example.bookstoreproject.domain.usecases.AddNewProductUseCase
import com.example.bookstoreproject.domain.usecases.AuthenticateUseCase
import com.example.bookstoreproject.domain.usecases.CreateUserUseCase
import com.example.bookstoreproject.domain.usecases.GetAllProductsFromAPIUseCase
import com.example.bookstoreproject.domain.usecases.GetAllProductsUseCase
import com.example.bookstoreproject.domain.usecases.GetProductByIDUseCase
import com.example.bookstoreproject.domain.usecases.RemoveProductFromCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val getAllProductsFromAPIUseCase: GetAllProductsFromAPIUseCase,
    private val addNewProductUseCase: AddNewProductUseCase,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getProductByIDUseCase: GetProductByIDUseCase,
    private val removeProductFromCartUseCase: RemoveProductFromCartUseCase
) :
    ViewModel() {

    private val _isUserAuth = MutableStateFlow<Boolean>(false)
    val isUserAuth = _isUserAuth.asStateFlow()

    private val _allProducts = MutableStateFlow<List<Book>>(emptyList())
    val allProducts = _allProducts.asStateFlow()

    private val _allProductsCart = MutableStateFlow<List<Product>>(emptyList())
    val allProductsCart = _allProductsCart.asStateFlow()

    fun authUser(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = authenticateUseCase(login, password)

            _isUserAuth.value = user != null


        }
    }


    fun createNewUser(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val user = User(username = login, password = password)
            createUserUseCase(user)
        }
    }

    fun getAllProductsFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getAllProductsFromAPIUseCase().execute()

            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                _allProducts.value = response.body()!!
            }
        }
    }

    fun getAllProductsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val productsCart = getAllProductsUseCase()

            if (productsCart.isNotEmpty()) {
                _allProductsCart.value = productsCart
            }
        }
    }


    fun addNewProduct(
        book: Book
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            val product = Product(
                id = book.id_book,
                name = book.book_name,
                price = book.price,
                year = book.year,
                imagepath = book.imagepath
            )

            addNewProductUseCase(product)
        }
    }

    fun isBookInCart(bookId: Int): Boolean {
        return runBlocking { getProductByIDUseCase.invoke(bookId) != null }
    }

    fun removeProductFromCart(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            removeProductFromCartUseCase(product)
            _allProductsCart.value = getAllProductsUseCase()

        }
    }


}