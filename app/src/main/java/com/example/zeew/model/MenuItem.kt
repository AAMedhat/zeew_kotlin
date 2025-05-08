package com.example.zeew.model

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResId: Int,
    val isSpicy: Boolean = false,
    val category: String
) 