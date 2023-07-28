package com.example.coffee_order_app

import android.view.View
import android.widget.ImageView
import com.example.coffee_order_app.ui.cart.CartItem
import com.example.coffee_order_app.ui.details.CoffeeItem
import com.example.coffee_order_app.ui.profile.User
import com.example.coffee_order_app.HomeActivity
import com.example.coffee_order_app.ui.order.OrderList

object Globals {
    var cartItemList = mutableListOf<CartItem>()
    var coffeeItem: CoffeeItem = CoffeeItem(0, 1, 1, false, 2, 3, 3.00)
    var loyaltyPoint: Int = 0
    var user: User = User("Hoang Phuc", "chphuc21@apcs.fitus.edu.vn", "0123456789", "227 Nguyen Van Cu Street, District 5, Ho Chi Minh City")
    var pointRedeeem: Int = 36

    public fun setLoyaltyCardView(loyaltyPoint: Int, view: View) {
        for (i in 1..loyaltyPoint) {
            val name = "loyalty_icon_" + i.toString()
            val id = R.id::class.java.getField(name).getInt(null)
            val imageView = view.findViewById<ImageView>(id)
            imageView.setImageResource(R.drawable.coffee_cup_fill)
        }
    }

    var onGoingOrder: MutableList<OrderList> = mutableListOf<OrderList>()
}