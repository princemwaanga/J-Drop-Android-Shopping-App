package com.example.myapp.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.databinding.FragmentProductDetailBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailFragment : Fragment() {
    private var _binding : FragmentProductDetailBinding ? = null
    private val binding get() = _binding!!
    private val cartViewModel : CartViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    private lateinit var id : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product_detail, container, false)
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)


        binding.floatingActionButton2.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_productDetailFragment_to_navigation_cart)
        }

        arguments?.getString("name")?.let {
            binding.tvProductDetailName.text = it
        }

        arguments?.getString("description")?.let {
            binding.tvProductDetailDescription.text = it
        }

        arguments?.getString("price")?.let {
            binding.tvProductDetailPrice.text = it
        }

        arguments?.getInt("imageResID")?.let {
            Glide.with(requireContext())
                .load(it)
                .into(binding.ivProductDetailImage)
        }

        var recProduct = Product(id, binding.tvProductDetailName.text.toString(), binding.tvProductDetailDescription.text.toString() , binding.tvProductDetailPrice.text.toString().toDouble(),"image",binding.ivProductDetailImage.id)
        binding.btnDetailsAddToCart.setOnClickListener{
            
            cartViewModel.addItem(CartItem(recProduct, 1))
        }
        
    }
    // Search function
//    fun getProductById(products:List<Product>, name: String): Product?{
//        return products.find { it.name == name }
//    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
