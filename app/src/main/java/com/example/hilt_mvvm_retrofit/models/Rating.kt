package com.example.hilt_mvvm_retrofit.models


import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Rating(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("rate")
    val rate: Double?
)