package com.idnp.danp_proyecto_final.screens.login.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    val state = MutableStateFlow(LoadingState.IDLE)

    fun register(
        email: String,
        password: String,
    ) = viewModelScope.launch{
        try {
            state.emit(LoadingState.LOADING)
            Firebase.auth.createUserWithEmailAndPassword(email, password)
            delay(1000)
            state.value = state.value.copy(successRegister = true)
            state.emit(LoadingState.LOADED)
        } catch (e: Exception) {
            state.emit(LoadingState.error(e.localizedMessage))
        }
    }
}