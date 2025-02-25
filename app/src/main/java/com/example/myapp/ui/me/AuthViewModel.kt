package com.example.myapp.ui.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class AuthViewModel : ViewModel() {
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

        private val fakeUserDatabase = mutableListOf(
            User("test@example.com", "password", "Test User")
        )

        fun login(email: String, password: String){
            viewModelScope.launch{
                _authState.value = AuthState.Loading
                delay(1000) // Simulate network delay

                val user = fakeUserDatabase.find{it.email == email && it.password == password}
                if(user != null){
                    _authState.value = AuthState.Success(user)
                }else{
                    _authState.value = AuthState.Error("Invalid credintials")
                }
            }
        }
        fun register(name: String, email: String, password: String){
            viewModelScope.launch {
                _authState.value = AuthState.Loading
                delay(1000) // Simulate network delay

                val existingUser = fakeUserDatabase.any { it.email == email }
                if(existingUser){
                    _authState.value = AuthState.Error("Email already registered")
                }else{
                    val newUser = User(email, password, name)
                    fakeUserDatabase.add(newUser)
                    _authState.value = AuthState.Success(newUser)
                }
            }
        }

    sealed class AuthState {
        object Loading : AuthState()
        data class Success(val user: User) : AuthState()
        data class Error(val message: String) : AuthState()
    }
}


data class User (
    val email: String,
    val password: String,
    val name: String
)
