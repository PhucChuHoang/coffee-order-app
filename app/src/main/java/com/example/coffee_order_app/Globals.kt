package com.example.coffee_order_app

import com.example.coffee_order_app.ui.cart.CartItem
import com.example.coffee_order_app.ui.details.CoffeeItem
import com.example.coffee_order_app.ui.profile.User

object Globals {
    var cartItemList = mutableListOf<CartItem>()
    var coffeeItem: CoffeeItem = CoffeeItem(0, 1, 1, false, 2, 3, 3.00)
    var loyaltyPoint: Int = 0
    var user: User = User("Hoang Phuc", "chphuc21@apcs.fitus.edu.vn", "0123456789", "227 Nguyen Van Cu Street, District 5, Ho Chi Minh City")
}