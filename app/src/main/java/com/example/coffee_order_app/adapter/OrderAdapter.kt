package com.example.coffee_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R

class OrderAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeNameTextView: TextView
        val locationTextView: TextView
        val priceTextView: TextView
        val dateTextView: TextView

        init {
            coffeeNameTextView = view.findViewById(R.id.order_coffee_name)
            locationTextView = view.findViewById(R.id.order_location)
            priceTextView = view.findViewById(R.id.order_price)
            dateTextView = view.findViewById(R.id.order_date)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.order_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.coffeeNameTextView.text = dataSet[position]
        viewHolder.locationTextView.text = Globals.user.address
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}