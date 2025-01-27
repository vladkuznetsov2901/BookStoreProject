package com.example.bookstoreproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoreproject.domain.models.Book
import com.example.bookstoreproject.domain.models.User
import com.example.bookstoreproject.domain.usecases.AuthenticateUseCase
import com.example.bookstoreproject.domain.usecases.CreateUserUseCase
import com.example.bookstoreproject.domain.usecases.GetAllProductsFromAPIUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val getAllProductsFromAPIUseCase: GetAllProductsFromAPIUseCase
) :
    ViewModel() {

    private val _isUserAuth = MutableStateFlow<Boolean>(false)
    val isUserAuth = _isUserAuth.asStateFlow()

    private val _allProducts = MutableStateFlow<List<Book>>(emptyList())
    val allProducts = _allProducts.asStateFlow()

    fun authUser(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = authenticateUseCase.invoke(login, password)

            _isUserAuth.value = user != null


        }
    }


    fun createNewUser(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val user = User(username = login, password = password)
            createUserUseCase.invoke(user)
        }
    }

    fun getAllProductsFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getAllProductsFromAPIUseCase.invoke().execute()

            if (response.isSuccessful && !response.body().isNullOrEmpty()) {
                _allProducts.value = response.body()!!
            }
        }
    }


}