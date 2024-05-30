package com.example.hilt_mvvm_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt_mvvm_retrofit.repository.ProductRepository
import com.example.hilt_mvvm_retrofit.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor
    (private val repository: ProductRepository) :ViewModel() {

    val productsLiveData :LiveData<List<Product>>
        get() = repository.product

    init {
        viewModelScope.launch {
            repository.getProducts()
        }
    }

}