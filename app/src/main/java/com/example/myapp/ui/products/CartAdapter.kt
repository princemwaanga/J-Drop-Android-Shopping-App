package com.example.myapp.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.databinding.ItemCartBinding

class CartAdapter(
    private val inflater: LayoutInflater,
    private var cartItems: MutableList<CartItem>,
    private val onRemoveClick: (CartItem)-> Unit
) :RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(private val binding: ItemCartBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem){
            binding.tvCartProductName.text = item.product.name
            binding.tvQuantity.text = "Qty: ${item.quantity}"
            binding.btnRemove.setOnClickListener{onRemoveClick(item)}
        }
    }
    fun updateList(newList: List<CartItem>){
        cartItems.clear()
        cartItems.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(ItemCartBinding.inflate(inflater, parent,false))
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItems[position])
    }
}