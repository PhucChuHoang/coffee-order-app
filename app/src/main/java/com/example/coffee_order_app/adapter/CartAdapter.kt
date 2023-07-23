package com.example.coffee_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.R

class CartAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeTypeTextView: TextView
        val detailsTextView: TextView
        val quantityTextView: TextView
        val priceTextView: TextView

        init {
            // Define click listener for the ViewHolder's View
            coffeeTypeTextView = view.findViewById(R.id.name_coffee)
            detailsTextView = view.findViewById(R.id.coffee_details)
            quantityTextView = view.findViewById(R.id.quantity)
            priceTextView = view.findViewById(R.id.price)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cart_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.coffeeTypeTextView.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }

}