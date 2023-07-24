package com.example.coffee_order_app.ui.cart

import androidx.lifecycle.ViewModel
import com.example.coffee_order_app.Globals
import com.example.coffee_order_app.ui.details.CoffeeItem

data class CartItem(
    val coffeeItem: CoffeeItem,
    val totalPrice: Double,
)
class CartViewModel:ViewModel() {
    fun addCartItem(cartItem: CartItem) {
        Globals.cartItemList.add(cartItem)
    }
    fun removeCartItem(cartItem: CartItem) {
        Globals.cartItemList.remove(cartItem)
    }
    fun getTotalPrice(): Double {
        var totalPrice: Double = 0.0
        for (cartItem in Globals.cartItemList) {
            totalPrice += cartItem.totalPrice
        }
        return totalPrice
    }
}