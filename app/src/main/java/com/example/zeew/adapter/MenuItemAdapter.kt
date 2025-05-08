package com.example.zeew.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zeew.R
import com.example.zeew.model.MenuItem

class MenuItemAdapter(
    private var items: List<MenuItem>,
    private val onItemClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuItemAdapter.MenuItemViewHolder>() {

    class MenuItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.itemName)
        val description: TextView = view.findViewById(R.id.itemDescription)
        val price: TextView = view.findViewById(R.id.itemPrice)
        val image: ImageView = view.findViewById(R.id.itemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return MenuItemViewHolder(view)
    }

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

    override fun getItemCount() = items.size

    fun updateItems(newItems: List<MenuItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun getItems(): List<MenuItem> = items
} 