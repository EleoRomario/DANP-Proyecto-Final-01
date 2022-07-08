package com.idnp.danp_proyecto_final.presentation.login.user


import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.utils.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private lateinit var _auth: FirebaseAuth

    private val _loginState = mutableStateOf(false)

    init {
        _auth = Firebase.auth
    }

    fun signInWithEmailAndPassword(email: String, password: String, navController: NavController) = viewModelScope.launch {
        try {
            Log.d("LOGIN","${email}${password}")
            loadingState.emit(LoadingState.LOADING)
            _auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    _loginState.value = true
                    navController.navigate(AppScreens.HomeScreen.route){
                       // popUpTo(AppScreens.LaunchingNavigation.route)
                    }
                } else {
                    _loginState.value = false
                }
            }
            if(_loginState.value) loadingState.emit(LoadingState.SUCCESS)
        } catch (exception: Exception) {
            Log.d("Firebase Authentication", "signInWithEmailAndPassword: ${exception.localizedMessage}")
        }
    }

    fun signInUserUsingGoogle(credential: AuthCredential, navController: NavController) = viewModelScope.launch {
        try {
            loadingState.emit(LoadingState.LOADING)
            Firebase.auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful){
                    _loginState.value = true
                    navController.navigate(AppScreens.HomeScreen.route){
                        /*popUpTo(AppScreens.LaunchingNavigation.route){
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