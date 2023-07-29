package com.example.coffee_order_app.ui.reward

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class RewardItem(
    val coffeeName: String,
    val timeOrdered: String,
    val point: Int
)

class RewardViewModel : ViewModel() {

}