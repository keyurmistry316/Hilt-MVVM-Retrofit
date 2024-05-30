package com.example.hilt_mvvm_retrofit.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hilt_mvvm_retrofit.models.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDB:RoomDatabase() {
    abstract fun getDao():Dao
}