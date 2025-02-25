package com.example.myapp.ui.eStore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapp.R
import com.example.myapp.databinding.FragmentBuildingBinding
import com.example.myapp.ui.products.CartItem
import com.example.myapp.ui.products.CartViewModel
import com.example.myapp.ui.products.Product
import com.example.myapp.ui.products.ProductClickListener
import com.example.myapp.ui.products.ProductViewModel

class BuildingFragment : Fragment(), ProductClickListener {

    private var _binding: FragmentBuildingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val cartViewModel : CartViewModel by viewModels()
    private val productViewModel : ProductViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBuildingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //Button to the Cart
        binding.BuildingfloatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_building_to_navigation_cart)
        }
        // Setting the RecyclerView
        binding.rvBuilding.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvBuilding.adapter = BuildingAdapter(layoutInflater, productViewModel.buildingList, this)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onProductClicked(product: Product) {
        val bundle = bundleOf("num" to product.id, "name" to product.name, "description" to product.description, "price" to product.price.toString(), "imageUrl" to product.imageUrl, "imageResID" to product.imageResID)
        findNavController().navigate(R.id.action_navigation_building_to_navigation_productDetailFragment, bundle)
    }

    override fun onAddToCartClicked(product: Product) {
        cartViewModel.addItem(CartItem(product, 1))
    }
}