package com.example.hilt_mvvm_retrofit.models


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @Embedded
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("title")
    val title: String?
)