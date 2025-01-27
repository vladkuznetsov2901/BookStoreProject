package com.example.bookstoreproject.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoreproject.domain.usecases.AuthenticateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val authenticateUseCase: AuthenticateUseCase) :
    ViewModel() {

    private val _isUserAuth = MutableStateFlow<Boolean>(false)
    val isUserAuth = _isUserAuth.asStateFlow()

    fun authUser(login: String, password: String) {
        viewModelScope.launch {
            val user = authenticateUseCase.invoke(login, password)

            _isUserAuth.value = user != null


        }
    }


}