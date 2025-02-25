package com.example.myapp.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.myapp.R
import com.example.myapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val loginViewModel =
//            ViewModelProvider(this).get(LoginViewModel::class.java)

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(validateInput(email, password)){
                authViewModel.login(email, password)
            }
        }

        binding.textView.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_notifications_to_navigation_register)
        }

        authViewModel.authState.observe(viewLifecycleOwner){
            state ->
            when(state){
                is AuthViewModel.AuthState.Loading->{
                    binding.loading.visibility = View.VISIBLE
                    binding.btnLogin.isEnabled = false
                }
                is AuthViewModel.AuthState.Success->{
                    binding.loading.visibility = View.GONE
                    navigateToSuccess(state.user)
                }
                is AuthViewModel.AuthState.Error->{
                    binding.loading.visibility = View.GONE
                    binding.btnLogin.isEnabled = true
                    Snackbar.make(requireView(), state.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
        
        return root
    }

    private fun navigateToSuccess(user: User) {
        val bundle = bundleOf("name" to user.name, "email" to user.email)
        findNavController().navigate(R.id.action_navigation_notifications_to_navigation_success , bundle)
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error = "Valid email required"
            isValid = false
        }

        if (password.length < 6){
            binding.etPassword.error = "Minimum 6 characters required"
            isValid = false
        }
        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}