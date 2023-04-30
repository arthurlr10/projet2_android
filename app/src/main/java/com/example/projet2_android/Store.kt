package com.example.projet2_android

import java.io.Serializable

data class Store(
    val storeId: Int,
    val name: String,
    val description: String,
    val pictureStore: String,
    val address: String,
    val zipcode: String,
    val city: String,
    val longitude: Double,
    val latitude: Double
) : Serializable


