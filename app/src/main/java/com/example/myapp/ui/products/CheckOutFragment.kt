package com.example.myapp.ui.products

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapp.R
import com.example.myapp.databinding.FragmentCheckOutBinding

class CheckOutFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private var _binding: FragmentCheckOutBinding? = null
    private val binding get() = _binding!!
    private val checkOutViewModel : CheckOutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckOutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Obtain the payment details
        checkOutViewModel.paymentDetails.observe(viewLifecycleOwner){value ->
            value.cardHolderName = binding.cardHolderNameLabel.text.toString()
            value.cvv = binding.cvv.text.toString()
            value.cardNumber = binding.cvv.toString()
            value.expiryDate = binding.expiryDate.text.toString()

        }

        binding.tvTotalPrice.text = "Total: K ${checkOutViewModel.TotalPrice}"

        binding.btnPlaceOrder.setOnClickListener{
            checkOutViewModel.placeOrder()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}