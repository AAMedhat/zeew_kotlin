package com.example.zeew.model

/**
 * Represents a menu item in the restaurant's menu.
 * This data class contains all the information needed to display and manage a menu item.
 *
 * @property id Unique identifier for the menu item
 * @property name Name of the menu item as displayed to users
 * @property description Detailed description of the menu item
 * @property price Price of the menu item in AED (Arab Emirates Dirham)
 * @property imageResId Resource ID for the menu item's image
 * @property isSpicy Indicates whether the menu item is spicy (defaults to false)
 * @property category The category this menu item belongs to (e.g., "Appetizers", "Main Course")
 */
data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageResId: Int,
    val isSpicy: Boolean = false,
    val category: String
) 