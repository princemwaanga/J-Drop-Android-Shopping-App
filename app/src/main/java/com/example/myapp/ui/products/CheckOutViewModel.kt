package com.example.myapp.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckOutViewModel : ViewModel() {
    val orderItems = CartRepository.cartItems

    private val _paymentDetails = MutableLiveData<PaymentDetails>()
    var paymentDetails : LiveData<PaymentDetails> = _paymentDetails

    var TotalPrice : Double = CartRepository.getTotalPrice()


    private val _orderPlaced = MutableLiveData<Boolean>()
    var orderPlaced : LiveData<Boolean> = _orderPlaced

    private val _paymentError = MutableLiveData<String>()
    var paymentError : LiveData<String> = _paymentError

    fun placeOrder(){
        _orderPlaced.value = true
        CartRepository.clear()
    }
}