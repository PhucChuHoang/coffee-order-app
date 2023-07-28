package com.example.coffee_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.R
import com.example.coffee_order_app.ui.cart.CartItem

class CartAdapter(private val cartItemList: List<CartItem>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCoffeeName: TextView = view.findViewById(R.id.name_coffee)
        val textViewCoffeeTotalPrice: TextView = view.findViewById(R.id.price)
        val textViewCoffeeQuantity: TextView = view.findViewById(R.id.quantity)
        val textViewCoffeeDetails: TextView = view.findViewById(R.id.coffee_details)
        val imageViewImageCoffee: ImageView = view.findViewById(R.id.image_coffee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartItemList[position]
        when(cartItem.coffeeItem.coffeeType) {
            1 -> {
                holder.textViewCoffeeName.text = "Americano"
                holder.imageViewImageCoffee.setImageResource(R.drawable.coffee1)
            }
            2 -> {
                holder.textViewCoffeeName.text = "Cappuccino"
                holder.imageViewImageCoffee.setImageResource(R.drawable.coffee2)
            }
            3 -> {
                holder.textViewCoffeeName.text = "Mocha"
                holder.imageViewImageCoffee.setImageResource(R.drawable.coffee3)
            }
            4 -> {
                holder.textViewCoffeeName.text = "Flat White"
                holder.imageViewImageCoffee.setImageResource(R.drawable.coffee4)
            }
        }
        val price = cartItem.totalPrice
        val formatted = "$" + String.format("%.2f", price)
        holder.textViewCoffeeTotalPrice.text = formatted
        val formattedQuantity = "x "+ cartItem.coffeeItem.quantity.toString()
        holder.textViewCoffeeQuantity.text = formattedQuantity
        holder.textViewCoffeeDetails.text = setDetails(
            cartItem.coffeeItem.shot,
            cartItem.coffeeItem.hotOrCold,
            cartItem.coffeeItem.size,
            cartItem.coffeeItem.ice
        )
    }

    private fun setDetails(shot: Int, hot_cold: Boolean, size: Int, ice: Int): String {
        var details: String = ""
        if (shot == 1) {
            details += "single"
        } else {
            details += "double"
        }
        if (hot_cold) {
            details += " | hot"
        } else {
            details += " | iced"
        }
        if (size == 1) {
            details += " | small"
        } else if (size == 2) {
            details += " | medium"
        } else {
            details += " | large"
        }
        if (ice == 1) {
            details += " | no ice"
        } else if (ice == 2) {
            details += " | normal ice"
        } else {
            details += " | full ice"
        }
        return details
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }
}