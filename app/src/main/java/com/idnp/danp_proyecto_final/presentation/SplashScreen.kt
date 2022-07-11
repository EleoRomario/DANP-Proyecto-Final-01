package com.idnp.danp_proyecto_final.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.navegation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    user: FirebaseUser?
){
    LaunchedEffect(key1 = true){
        delay(2000)
        navController.popBackStack()
        if(user == null)
            navController.navigate(AppScreens.LoginScreen.route)
        else
            navController.navigate(AppScreens.HomeScreen.route)
    }
    Scaffold(
    ) {
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xBFB7B7B8))
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            logoPeru()
        }
    }
}

@Composable
fun logoPeru(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo), contentDescription = "Peru",

            )
        Text(text = "Perú", fontSize = 25.sp)
        Text(text = "Destino mágico")
    }
}

@Preview(showBackground = true)
@Composable
fun previewPeru(){
    //SplashScreen()
}