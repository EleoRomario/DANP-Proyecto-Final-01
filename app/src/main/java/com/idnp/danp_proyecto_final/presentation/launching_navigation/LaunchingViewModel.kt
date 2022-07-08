package com.idnp.danp_proyecto_final.presentation.launching_navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.utils.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LaunchingViewModel: ViewModel() {

    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val _loginState = mutableStateOf(false)

    fun signInUserUsingGoogle(credential: AuthCredential, navController: NavController) = viewModelScope.launch {
        try {
            loadingState.emit(LoadingState.LOADING)
            Firebase.auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful){
                    _loginState.value = true
                    navController.navigate(AppScreens.HomeScreen.route){
                       /* popUpTo(AppScreens.LaunchingNavigation.route){
                            inclusive = false
                        }*/
                    }
                }else {
                    _loginState.value = false
                }
            }
            if (_loginState.value) loadingState.emit(LoadingState.SUCCESS)

        } catch (e: Exception) {
            loadingState.emit(LoadingState.error(e.stackTraceToString()))
        }
    }
}