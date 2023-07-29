package com.example.coffee_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.R
import com.example.coffee_order_app.ui.reward.RewardItem

class HistoryRewardAdapter(private val dataSet: MutableList<RewardItem>) : RecyclerView.Adapter<HistoryRewardAdapter.ViewHolder>() {

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
        val rewardItem = dataSet[position]
        viewHolder.coffeeTypeTextView.text = rewardItem.coffeeName
        viewHolder.dateTextView.text = rewardItem.timeOrdered
        val pointString = "+ " + rewardItem.point.toString() + " Pts"
        viewHolder.pointTextView.text = pointString
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}