package com.example.hilt_mvvm_retrofit.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.hilt_mvvm_retrofit.models.Product

@Dao
interface Dao {

    @Upsert
    suspend fun addProducts(products: List<Product>)

    @Query("SELECT * FROM Product")
    suspend fun getProducts():List<Product>
}