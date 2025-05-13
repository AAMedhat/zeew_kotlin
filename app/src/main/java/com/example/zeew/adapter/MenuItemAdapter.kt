package com.example.zeew.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zeew.R
import com.example.zeew.model.MenuItem

/**
 * RecyclerView adapter for displaying menu items in a list or grid.
 * This adapter handles the display of menu items with category headers
 * and click events for adding items to the cart.
 *
 * @property items List of menu items to display
 * @property onItemClick Callback function to handle item click events
 */
class MenuItemAdapter(
    private var items: List<MenuItem>,
    private val onItemClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>() {

    /**
     * ViewHolder class for menu items that holds references to the UI elements
     * in the item layout.
     *
     * @property name TextView for the menu item name
     * @property description TextView for the menu item description
     * @property price TextView for the menu item price
     * @property image ImageView for the menu item image
     */
    class MenuItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.itemName)
        val description: TextView = view.findViewById(R.id.itemDescription)
        val price: TextView = view.findViewById(R.id.itemPrice)
        val image: ImageView = view.findViewById(R.id.itemImage)
    }

    /**
     * Creates a new ViewHolder by inflating the item_menu layout.
     *
     * @param parent The parent ViewGroup
     * @param viewType The view type of the new View (not used in this implementation)
     * @return A new MenuItemViewHolder that holds a View of the item_menu layout
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return MenuItemViewHolder(view)
    }

    /**
     * Binds a menu item to a ViewHolder. This method sets up the item's
     * visual elements and click handler. It also handles the display of
     * category headers when needed.
     *
     * @param holder The ViewHolder to bind data to
     * @param position The position of the item in the data set
     */
    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val item = items[position]
        
        // Add category header if this is the first item of its category
        if (position == 0 || items[position - 1].category != item.category) {
            holder.itemView.findViewById<TextView>(R.id.categoryHeader)?.visibility = View.VISIBLE
            holder.itemView.findViewById<TextView>(R.id.categoryHeader)?.text = item.category
        } else {
            holder.itemView.findViewById<TextView>(R.id.categoryHeader)?.visibility = View.GONE
        }

        holder.name.text = item.name
        holder.description.text = item.description
        holder.price.text = "AED ${item.price}"
        holder.image.setImageResource(item.imageResId)

        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    /**
     * Returns the total number of items in the data set.
     *
     * @return The total number of menu items
     */
    override fun getItemCount() = items.size

    /**
     * Updates the list of menu items and refreshes the display.
     *
     * @param newItems The new list of menu items to display
     */
    fun updateItems(newItems: List<MenuItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    /**
     * Returns the current list of menu items.
     *
     * @return The current list of menu items
     */
    fun getItems(): List<MenuItem> = items
} 