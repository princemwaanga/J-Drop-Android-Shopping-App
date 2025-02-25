package com.example.myapp.ui.me

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.FragmentRegisterBinding
import com.example.myapp.databinding.FragmentSuccessBinding
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnRegister.setOnClickListener{
            val name = binding.registerName.text.toString()
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()

            if(validateInput(name, email, password)){
                authViewModel.register(name, email, password)
            }
            authViewModel.authState.observe(viewLifecycleOwner){state->
                when(state){
                    is AuthViewModel.AuthState.Loading->{
                        binding.registerProgressBar.visibility = View.VISIBLE
                        binding.btnRegister.isEnabled = false
                    }
                    is AuthViewModel.AuthState.Success->{
                        binding.registerProgressBar.visibility = View.GONE
                        findNavController().navigateUp()
                    }
                    is AuthViewModel.AuthState.Error->{
                        binding.registerProgressBar.visibility = View.GONE
                        binding.btnRegister.isEnabled = true
                        Snackbar.make(requireView(), state.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
        return root
    }

    private fun validateInput(name: String, email: String, password: String): Boolean {
        var isValid = true

        if(name.isEmpty()){
            binding.registerName.error = "Name required"
            isValid = false
        }

        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.registerEmail.error = "Valid email required"
            isValid = false
        }

        if (password.length < 6){
            binding.registerPassword.error = "Valid email required"
            isValid = false
        }
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}