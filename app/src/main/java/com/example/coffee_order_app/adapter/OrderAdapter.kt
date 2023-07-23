package com.example.coffee_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.R

class OrderAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeNameTextView: TextView
        val locationTextView: TextView
        val priceTextView: TextView
        val dateTextView: TextView

        init {
            // Define click listener for the ViewHolder's View
            coffeeNameTextView = view.findViewById(R.id.order_coffee_name)
            locationTextView = view.findViewById(R.id.order_location)
            priceTextView = view.findViewById(R.id.order_price)
            dateTextView = view.findViewById(R.id.order_date)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.order_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.coffeeNameTextView.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }
}