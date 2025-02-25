package com.example.myapp.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.databinding.ItemProductBinding

class ProductAdapter(
    private val inflater: LayoutInflater,
    private val productList: List<Product>,
    private val clickListener: ProductClickListener

): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)//, View.OnClickListener, View.OnLongClickListener
    {

        fun bind(product: Product){
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = "Price: K${product.price}"
            Glide.with(binding.root)
                .load(product.imageResID)
                .placeholder(R.drawable.ic_dashboard_black_24dp)
                .error(R.drawable.ic_dashboard_black_24dp)
                .into(binding.ivProductImage)

            binding.btnAddToCart.setOnClickListener{
                //onAddToCart(product)
                clickListener.onAddToCartClicked(product)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent,false)
        return ProductViewHolder(ItemProductBinding.inflate(inflater, parent,false))
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener{
            Toast.makeText(
                holder.itemView.context,
                productList[position].name,
                Toast.LENGTH_LONG
            ).show()
            //onItemClick(product)
            clickListener.onProductClicked(product)
        }
        }

}


