package com.example.coffee_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.R
import com.example.coffee_order_app.ui.order.OrderList

class OrderAdapter(private val orderList: MutableList<OrderList>): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeNameTextView: TextView = view.findViewById(R.id.order_coffee_name)
        val orderLocationTextView: TextView = view.findViewById(R.id.order_location)
        val orderPriceTextView: TextView = view.findViewById(R.id.order_price)
        val orderDateTextView: TextView = view.findViewById(R.id.order_date)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.order_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val orderItem = orderList[position]
        viewHolder.coffeeNameTextView.text = orderItem.coffeeName
        viewHolder.orderLocationTextView.text = orderItem.location
        viewHolder.orderPriceTextView.text = "$" + String.format("%.2f", orderItem.price)
        viewHolder.orderDateTextView.text = orderItem.date
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}