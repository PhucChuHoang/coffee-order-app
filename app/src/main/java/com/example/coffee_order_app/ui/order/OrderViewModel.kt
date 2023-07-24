package com.example.coffee_order_app.ui.order

import androidx.lifecycle.ViewModel

data class OrderList(
    val coffeeName: String,
    val location: String,
    val price: Double,
    val date: String
)

class OrderViewModel: ViewModel() {
}