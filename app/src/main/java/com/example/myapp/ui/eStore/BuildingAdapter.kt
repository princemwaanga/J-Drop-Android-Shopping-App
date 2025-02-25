package com.example.myapp.ui.eStore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.databinding.ItemProductBinding
import com.example.myapp.ui.products.Product
import com.example.myapp.ui.products.ProductClickListener

class BuildingAdapter(
    private val inflater: LayoutInflater,
    private val buildingList: List<Product>,
    private val clickListener: ProductClickListener
) : RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {
    inner class BuildingViewHolder(private val binding: ItemProductBinding) :
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        return BuildingViewHolder(ItemProductBinding.inflate(inflater, parent,false))
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        val product = buildingList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener{
            Toast.makeText(
                holder.itemView.context,
                buildingList[position].name,
                Toast.LENGTH_LONG
            ).show()
            clickListener.onProductClicked(product)
        }
    }

    override fun getItemCount() = buildingList.size


}
