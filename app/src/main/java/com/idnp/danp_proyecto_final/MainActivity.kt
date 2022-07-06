package com.idnp.danp_proyecto_final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.idnp.danp_proyecto_final.navegation.AppNavigation
import com.idnp.danp_proyecto_final.presentation.login.user.AuthViewModel
import com.idnp.danp_proyecto_final.ui.theme.DANPProyectoFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val loginViewModel = viewModel(modelClass = AuthViewModel::class.java)

            DANPProyectoFinalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavigation(AuthViewModel = loginViewModel)
                }
            }
        }
    }
}