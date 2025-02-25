package com.example.myapp.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {


    val cartItems = CartRepository.cartItems

    private val _mutableLiveData = MutableLiveData<Double>()
    var liveTotalPrice : LiveData<Double> = _mutableLiveData

    // Auto update the totals textview figure
    fun startUpdates(){
        viewModelScope.launch {
            while (true){
                _mutableLiveData.postValue(CartRepository.getTotalPrice())
                delay(500)
            }
        }
    }

    fun removeItem(item: CartItem){
        CartRepository.removeFromCart(item)
    }
    fun addItem(item: CartItem){
        CartRepository.addToCart(item)
    }

}