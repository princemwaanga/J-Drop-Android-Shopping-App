package com.example.myapp.ui.products

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    val id : String,
    val name : String,
    val description: String,
    val price : Double,
    val imageUrl : String,
    val imageResID: Int
): Parcelable

