package com.example.coffee_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.R

class HistoryRewardAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<HistoryRewardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coffeeTypeTextView: TextView
        val dateTextView: TextView
        val pointTextView: TextView

        init {
            coffeeTypeTextView = view.findViewById(R.id.coffee_type_textview)
            dateTextView = view.findViewById(R.id.date_textview)
            pointTextView = view.findViewById(R.id.point_textview)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.history_reward_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.coffeeTypeTextView.text = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}