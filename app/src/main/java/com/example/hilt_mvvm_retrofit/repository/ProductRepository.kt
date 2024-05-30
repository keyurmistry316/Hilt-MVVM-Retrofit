package com.example.hilt_mvvm_retrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hilt_mvvm_retrofit.MyApplication
import com.example.hilt_mvvm_retrofit.db.Dao
import com.example.hilt_mvvm_retrofit.models.Product
import com.example.hilt_mvvm_retrofit.retrofit.FakerAPI
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: FakerAPI,
    private val dao: Dao) {

    private val _products = MutableLiveData<List<Product>>()
        val product:LiveData<List<Product>>
        get() = _products

    suspend fun getProducts() {

        if (MyApplication.isNetworkAvailable){
            val result = api.getProducts("products")
            if(result.isSuccessful && result.body() != null){
                result.body()?.let {
                    dao.addProducts(it)
                    _products.postValue(dao.getProducts())
                }

            }
        }
        else{
            _products.postValue(dao.getProducts())
        }
    }
}