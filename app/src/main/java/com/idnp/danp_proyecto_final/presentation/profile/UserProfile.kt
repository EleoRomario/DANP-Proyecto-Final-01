package com.idnp.danp_proyecto_final.presentation.profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.idnp.danp_proyecto_final.presentation.login.user.LoginViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.presentation.components.TopBarBack
import com.idnp.danp_proyecto_final.presentation.components.TopBarPeru

@Composable
fun ProfileScreen(
    navController: NavController
){
    val user = Firebase.auth.currentUser
    Scaffold(
        topBar = {
            TopBarBack(navController)
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)){
                ProfileContent(navController, user)
            }
        }
    )
}

@Composable
fun ProfileContent(
    navController: NavController,
    user: FirebaseUser?
){
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(painter = rememberImagePainter(
            data = user?.photoUrl),
            contentDescription = "photo_url",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(RoundedCornerShape(20.dp))
        )
        Text(text = user?.displayName.toString(), fontSize = 20.sp)
        Text(text = user?.email.toString(), color = Color.LightGray)
        OutlinedButton(
            onClick = {
                Firebase.auth.signOut()
                navController.navigate(route = AppScreens.LoginScreen.route)
                Toast.makeText(context, "Successfully Signed Out", Toast.LENGTH_SHORT).show()
            },
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Sign Out")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun preveProfile(){
    val viewModel = LoginViewModel()
    //ProfileScreen(viewModel)
}