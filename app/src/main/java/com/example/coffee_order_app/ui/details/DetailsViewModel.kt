package com.example.coffee_order_app.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class CoffeeItem(
    var coffeeType: Int,
    var quantity: Int,
    var shot: Int,
    // true = hot, false = cold
    var hotOrCold: Boolean,
    var size: Int,
    var ice: Int,
    var price: Double
)

class DetailsViewModel : ViewModel() {
    var coffeeItem: CoffeeItem = CoffeeItem(1, 1, 1, false, 2, 3, 3.00)
}