package com.example.zeew.model

/**
 * Represents a category of menu items in the restaurant's menu.
 * Categories help organize menu items into logical groups for better navigation.
 *
 * @property id Unique identifier for the category
 * @property name Display name of the category
 * @property icon Optional icon resource name for the category
 * @property description Optional description of what this category contains
 */
data class Category(
    val id: Int,
    val name: String,
    val icon: String? = null,
    val description: String? = null
) 