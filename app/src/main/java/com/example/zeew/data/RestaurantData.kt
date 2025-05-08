package com.example.zeew.data

import com.example.zeew.model.Category
import com.example.zeew.model.MenuItem
import com.example.zeew.R

object RestaurantData {
    val categories = listOf(
        Category(1, "Trending 🔥", description = "Most popular dishes"),
        Category(2, "Free Soup with Celebration", description = "Special offers"),
        Category(3, "Starter", description = "Begin your meal"),
        Category(4, "Noodles", description = "Asian noodle dishes"),
        Category(5, "Rice", description = "Rice specialties"),
        Category(6, "Beverages", description = "Drinks and refreshments")
    )

    val menuItems = listOf(
        // Trending Items
        MenuItem(
            id = 1,
            name = "Chicken Schezwan Fried Rice",
            description = "Golden fried Chicken pieces wok-tossed with hotand spicy schezwan fried rice with vegetables like green onions, carrots, and bell peppers",
            price = 31.00,
            imageResId = R.drawable.item,
            category = "Trending 🔥",
            isSpicy = true
        ),
        MenuItem(
            id = 2,
            name = "Chicken Chilli Basil Noodles",
            description = "Noodles wok-tossed with fried chicken, egg and vegetables with the added zest of chilli and fragrance of basil",
            price = 31.00,
            imageResId = R.drawable.item,
            category = "Trending 🔥",
            isSpicy = true
        ),

        // Free Soup Items
        MenuItem(
            id = 3,
            name = "Celebration Special Soup",
            description = "Complimentary soup with your order - A warming blend of vegetables and herbs",
            price = 0.00,
            imageResId = R.drawable.item,
            category = "Free Soup with Celebration"
        ),

        // Starters
        MenuItem(
            id = 4,
            name = "Vegetable Spring Rolls",
            description = "Crispy rolls filled with shredded vegetables and glass noodles, served with sweet chili sauce",
            price = 15.00,
            imageResId = R.drawable.item,
            category = "Starter"
        ),
        MenuItem(
            id = 5,
            name = "Chicken Dim Sum",
            description = "Steamed dumplings filled with minced chicken and herbs",
            price = 18.00,
            imageResId = R.drawable.item,
            category = "Starter"
        ),

        // Noodles
        MenuItem(
            id = 6,
            name = "Pad Thai",
            description = "Classic Thai rice noodles with tofu, shrimp, peanuts, and tamarind sauce",
            price = 28.00,
            imageResId = R.drawable.item,
            category = "Noodles"
        ),
        MenuItem(
            id = 7,
            name = "Singapore Noodles",
            description = "Thin rice noodles with curry powder, vegetables, and your choice of protein",
            price = 26.00,
            imageResId = R.drawable.item,
            category = "Noodles"
        ),

        // Rice Dishes
        MenuItem(
            id = 8,
            name = "Yang Zhou Fried Rice",
            description = "Classic Chinese fried rice with shrimp, char siu pork, and vegetables",
            price = 25.00,
            imageResId = R.drawable.item,
            category = "Rice"
        ),
        MenuItem(
            id = 9,
            name = "Thai Green Curry with Rice",
            description = "Aromatic green curry with coconut milk, vegetables, and jasmine rice",
            price = 29.00,
            imageResId = R.drawable.item,
            category = "Rice",
            isSpicy = true
        ),

        // Beverages
        MenuItem(
            id = 10,
            name = "Thai Iced Tea",
            description = "Sweet and creamy Thai tea with milk",
            price = 12.00,
            imageResId = R.drawable.item,
            category = "Beverages"
        ),
        MenuItem(
            id = 11,
            name = "Fresh Coconut Water",
            description = "Natural coconut water served in the shell",
            price = 15.00,
            imageResId = R.drawable.item,
            category = "Beverages"
        )
    )

    val restaurantInfo = mapOf(
        "name" to "Mandarin Oak",
        "description" to "Chinese, Thai, Asian, Noodles, Dumplings",
        "rating" to 4.7,
        "totalRatings" to "1,066",
        "deliveryFee" to 6.50,
        "deliveryTime" to "24 mins",
        "logoResId" to R.drawable.resturant,
        "headerResId" to R.drawable.example
    )
} 