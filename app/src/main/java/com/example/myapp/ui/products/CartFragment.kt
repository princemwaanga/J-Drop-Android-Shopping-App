package com.example.myapp.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.databinding.FragmentCartBinding


/**
 * A simple [Fragment] subclass.
 * Use the [CartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val cartViewModel : CartViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_cart, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartViewModel.liveTotalPrice.observe(viewLifecycleOwner) { value ->
            binding.cartTotal.text = "Total: K ${value.toString()}"
        }
        // Start the coroutines
        cartViewModel.startUpdates()
        binding.btnContinue.setOnClickListener{
            findNavController().navigateUp()
        }
        // CheckOut Button
        binding.btnCheckout.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_cart_to_navigation_CheckOut)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = CartAdapter(layoutInflater, mutableListOf()){
            cartItem -> cartViewModel.removeItem(cartItem)
        }
        cartViewModel.cartItems.observe(viewLifecycleOwner, Observer {
            items -> (binding.recyclerView.adapter as CartAdapter).updateList(items) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}