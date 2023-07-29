package com.example.coffee_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.R
import com.example.coffee_order_app.ui.order.OrderList
import com.example.coffee_order_app.ui.redeem.RedeemItem
import java.text.SimpleDateFormat
import java.util.Date

class RedeemAdapter(private val dataSet: MutableList<RedeemItem>) : RecyclerView.Adapter<RedeemAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rewardNameTextView = view.findViewById<TextView>(R.id.reward_name)
        val rewardImageView = view.findViewById<ImageView>(R.id.redeem_image)
        val redeemButton = view.findViewById<Button>(R.id.redeem_button)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.reward_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val rewardItem = dataSet[position]
        when(rewardItem.coffeeName) {
            "Americano" -> {
                viewHolder.rewardImageView.setImageResource(R.drawable.coffee1)
                viewHolder.redeemButton.text = "40 pts"
            }
            "Cappuccino" -> {
                viewHolder.rewardImageView.setImageResource(R.drawable.coffee2)
                viewHolder.redeemButton.text = "50 pts"
            }
            "Mocha" -> {
                viewHolder.rewardImageView.setImageResource(R.drawable.coffee3)
                viewHolder.redeemButton.text = "50 pts"
            }
            "Flat White" -> {
                viewHolder.rewardImageView.setImageResource(R.drawable.coffee4)
                viewHolder.redeemButton.text = "50 pts"
            }
        }
        viewHolder.rewardNameTextView.text = rewardItem.coffeeName
        viewHolder.redeemButton.setOnClickListener { v ->
            val position = viewHolder.adapterPosition
            val rewardItem = dataSet[position]
            if(Globals.pointRedeeem < rewardItem.point) {
                Toast.makeText(v.context, "Not enough points", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Globals.pointRedeeem -= rewardItem.point
            val dateFormat = SimpleDateFormat("dd MMMM | HH:mm aa")
            val currentDate = dateFormat.format(Date())
            val orderItem = OrderList(
                rewardItem.coffeeName,
                Globals.user.address,
                0.0,
                currentDate
            )
            Globals.onGoingOrder.add(orderItem)
            Toast.makeText(v.context, "Redeemed successfully", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}