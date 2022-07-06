package com.idnp.danp_proyecto_final.presentation.components

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.idnp.danp_proyecto_final.presentation.login.user.AuthViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.idnp.danp_proyecto_final.R

@Composable
fun ProfileScreen(
    viewModel: AuthViewModel,
    onNavLogin: () -> Unit,
){

    val user = viewModel.currentUser
    user?.let {
        val name = user.displayName
        val email = user.email
        val photoUrl = user.photoUrl

        val emailVerified = user.isEmailVerified
        val uid = user.uid


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(painter = rememberImagePainter(data = photoUrl,
                builder = {
                    placeholder(R.drawable.placeholder)

                }),
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
            if (name != null) {
                Text(name)
            }
        }
        Divider(color = Color.LightGray)

        Surface(
            modifier = Modifier
                .clickable(
                    onClick = {
                       viewModel.signOut()
                    }
                )
                .fillMaxWidth(),
            color = Color.White,
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 16.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "logOut")
                Text(text = "Cerrar Sesion")
            }
        }

        LaunchedEffect(key1 = viewModel.hasUser){
            if(viewModel.hasUser){
                onNavLogin.invoke()
            }
        }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun preveProfile(){
    val viewModel = AuthViewModel()
    //ProfileScreen(viewModel)
}