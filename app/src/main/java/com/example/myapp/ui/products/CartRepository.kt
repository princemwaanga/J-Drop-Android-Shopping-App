package com.example.myapp.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object CartRepository {
    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems : LiveData<MutableList<CartItem>>
        get() = _cartItems

    fun addToCart(item : CartItem){
        val currentList = _cartItems.value ?: mutableListOf()
        val existingItem = currentList.find { it.product.id == item.product.id }
        existingItem?.let { it.quantity += item.quantity } ?: currentList.add(item)
        _cartItems.postValue(currentList)
    }
//    fun removeFromCart(item: CartItem){
//        val currentList = _cartItems.value ?: mutableListOf()
//        currentList.remove(item)
//        _cartItems.postValue(currentList)
//    }
    fun removeFromCart(itemToRemove: CartItem){
        val currentList = _cartItems.value ?: emptyList()
        val newList = currentList.toMutableList().apply {
            val existingItem = find { it.product.id == itemToRemove.product.id }
            if (existingItem != null){
                if (existingItem.quantity > 1){
                    val index = indexOf(existingItem)
                    set(index, existingItem.copy(quantity = existingItem.quantity - 1))
                }else{
                    remove(existingItem)
                }
            }
        }
    _cartItems.value = newList
    }
    fun getTotalPrice(): Double {
        return _cartItems.value?.sumOf { it.product.price * it.quantity } ?: 0.0
    }
    fun clear(){
        _cartItems.value = mutableListOf()
    }

}
