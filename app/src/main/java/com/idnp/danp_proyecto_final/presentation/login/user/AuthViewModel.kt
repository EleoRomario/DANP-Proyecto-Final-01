package com.idnp.danp_proyecto_final.presentation.login.user

import android.content.Context
import android.net.wifi.hotspot2.pps.Credential
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.domain.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())

    fun signWithCredential(credential: AuthCredential) = viewModelScope.launch {
        try {
            loginUiState = loginUiState.copy(isLoading = true)

            loginUiState = loginUiState.copy(loginError = null)

            repository.signInWithCredential(
                credential
            ){ isSuccessful ->
                if(isSuccessful){
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else{
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        }catch (e: Exception){
        loginUiState = loginUiState.copy(loginError =  e.localizedMessage)
        e.printStackTrace()
        }finally {
        loginUiState = loginUiState.copy(isLoading = false)
        }
    }
    fun signOut() {
        repository.signOut()
    }

}

data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null
)