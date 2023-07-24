package com.example.coffee_order_app.ui.profile

import androidx.lifecycle.ViewModel

data class User(
    var name: String,
    var email: String,
    var phone_number: String,
    var address: String
)

class ProfileViewModel: ViewModel() {

}