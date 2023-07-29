package com.example.coffee_order_app

import android.view.View
import android.widget.ImageView
import com.example.coffee_order_app.ui.cart.CartItem
import com.example.coffee_order_app.ui.details.CoffeeItem
import com.example.coffee_order_app.ui.profile.User
import com.example.coffee_order_app.ui.order.OrderList
import com.example.coffee_order_app.ui.redeem.RedeemItem
import com.example.coffee_order_app.ui.reward.RewardItem

object Globals {
    var cartItemList = mutableListOf<CartItem>()
    var coffeeItem: CoffeeItem = CoffeeItem(0, 1, 1, false, 2, 3, 3.00)
    var loyaltyPoint: Int = 0
    var maxLoyalPoint: Int = 8
    var user: User = User("Hoang Phuc", "chphuc21@apcs.fitus.edu.vn", "0123456789", "227 Nguyen Van Cu Street, District 5, Ho Chi Minh City")
    var pointRedeeem: Int = 0

    public fun setLoyaltyCardView(loyaltyPoint: Int, view: View) {
        for (i in 1..Globals.maxLoyalPoint) {
            val name = "loyalty_icon_" + i.toString()
            val id = view.resources.getIdentifier(name, "id", view.context.packageName)
            val imageView = view.findViewById<ImageView>(id)

            if (i <= loyaltyPoint) {
                imageView.setImageResource(R.drawable.coffee_cup_fill)
            } else {
                imageView.setImageResource(R.drawable.coffee_cup_empty)
            }
        }
    }

    var onGoingOrder: MutableList<OrderList> = mutableListOf<OrderList>()
    var historyOrder: MutableList<OrderList> = mutableListOf<OrderList>()
    var rewardItemHistory: MutableList<RewardItem> = mutableListOf<RewardItem>()
    val redeemItemList: MutableList<RedeemItem> = mutableListOf<RedeemItem>(
        RedeemItem("Americano", 40),
        RedeemItem("Cappuccino", 50),
        RedeemItem("Mocha", 50),
        RedeemItem("Flat White", 50)
    )
}