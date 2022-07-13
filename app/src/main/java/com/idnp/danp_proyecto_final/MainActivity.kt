package com.idnp.danp_proyecto_final

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.domain.viewsmodel.DataStoreViewModel
import com.idnp.danp_proyecto_final.navegation.AppNavigation
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentosViewModel
import com.idnp.danp_proyecto_final.presentation.login.user.LoginViewModel
import com.idnp.danp_proyecto_final.ui.theme.DANPProyectoFinalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DANPProyectoFinalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val auth = Firebase.auth
                    val currentUser = auth.currentUser
                    val viewModel: DepartamentosViewModel = hiltViewModel()
                    val state = viewModel.state.value

                    if(!state.isLoading){
                        AppNavigation(state, currentUser)
                    }
                    //CustomNotification()
                }
            }
        }
    }
}