package com.example.zeew.data

import com.example.zeew.model.Category
import com.example.zeew.model.MenuItem
import com.example.zeew.R

/**
 * Singleton object that holds all the static data for the restaurant app.
 * This includes menu categories, menu items, and general restaurant information.
 * In a production environment, this data would typically come from a backend server.
 */
object RestaurantData {
    /**
     * List of all menu categories available in the restaurant.
     * Categories are used to organize menu items and provide navigation structure.
     */
    val categories = listOf(
        Category(1, "Trending 🔥", description = "Most popular dishes"),
        Category(2, "Free Soup with Celebration", description = "Special offers"),
        Category(3, "Starter", description = "Begin your meal"),
        Category(4, "Noodles", description = "Asian noodle dishes"),
        Category(5, "Rice", description = "Rice specialties"),
        Category(6, "Beverages", description = "Drinks and refreshments")
    )

    /**
     * Complete list of menu items available in the restaurant.
     * Each item contains detailed information including name, description,
     * price, category, and whether it's spicy.
     */
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

    /**
     * General information about the restaurant including name, description,
     * ratings, delivery details, and image resources.
     *
     * Keys in the map:
     * - name: Restaurant name
     * - description: Short description of cuisine types
     * - rating: Average customer rating (out of 5)
     * - totalRatings: Total number of customer ratings
     * - deliveryFee: Delivery fee in AED
     * - deliveryTime: Estimated delivery time
     * - logoResId: Resource ID for restaurant logo
     * - headerResId: Resource ID for header image
     */
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