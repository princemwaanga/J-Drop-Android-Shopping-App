package com.example.myapp.ui.products

data class PaymentDetails (
    var cardNumber: String,
    var expiryDate : String,
    var cvv : String,
    var cardHolderName : String
)
