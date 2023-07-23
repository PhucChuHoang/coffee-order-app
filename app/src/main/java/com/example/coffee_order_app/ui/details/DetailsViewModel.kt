package com.example.coffee_order_app.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {
    // LiveData for coffee details
    private val _coffeeName = MutableLiveData<String>()
    val coffeeName: LiveData<String> get() = _coffeeName

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> get() = _quantity

    private val _shot = MutableLiveData<Boolean>()
    val shot: LiveData<Boolean> get() = _shot

    private val _dineIn = MutableLiveData<Boolean>()
    val dineIn: LiveData<Boolean> get() = _dineIn

    private val _size = MutableLiveData<String>()
    val size: LiveData<String> get() = _size

    private val _ice = MutableLiveData<Boolean>()
    val ice: LiveData<Boolean> get() = _ice

    // Functions to update the values
    fun setCoffeeName(name: String) {
        _coffeeName.value = name
    }

    fun setQuantity(quantity: Int) {
        _quantity.value = quantity
    }

    fun setShot(shot: Boolean) {
        _shot.value = shot
    }

    fun setDineIn(dineIn: Boolean) {
        _dineIn.value = dineIn
    }

    fun setSize(size: String) {
        _size.value = size
    }

    fun setIce(ice: Boolean) {
        _ice.value = ice
    }
}