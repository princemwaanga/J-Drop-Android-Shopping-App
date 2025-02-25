package com.example.myapp.ui.me

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.myapp.R
import com.example.myapp.databinding.FragmentSuccessBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SuccessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuccessFragment : Fragment() {

    private var _binding : FragmentSuccessBinding? = null
    private val binding get() = _binding!!
    //private val authViewModel : AuthViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_success, container, false)
        _binding = FragmentSuccessBinding.inflate(inflater, container, false)
        val root: View = binding.root

        arguments?.getString("name")?.let {
            binding.name.text = it
        }
        arguments?.getString("email")?.let {
            binding.email.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}