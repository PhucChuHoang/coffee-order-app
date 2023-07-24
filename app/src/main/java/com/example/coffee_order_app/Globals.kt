package com.example.coffee_order_app

import com.example.coffee_order_app.ui.cart.CartItem
import com.example.coffee_order_app.ui.details.CoffeeItem

object Globals {
    var cartItemList = mutableListOf<CartItem>()
    var coffeeItem: CoffeeItem = CoffeeItem(0, 1, 1, false, 2, 3, 3.00)
    val loyaltyPoint: Int = 0
}