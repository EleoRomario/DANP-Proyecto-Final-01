package com.idnp.danp_proyecto_final.presentation.login.user

import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.presentation.logoPeru
import com.idnp.danp_proyecto_final.ui.theme.Primary

@Composable
fun LoginScreen(
    loginViewModel: AuthViewModel = viewModel(),
    onNavToHomePage: () -> Unit,
){

    val loginUiState = loginViewModel?.loginUiState

    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoginHead()
            Spacer(modifier = Modifier.height(10.dp))
            LoginForm(
                onNavToHomePage,
                loginViewModel,
                loginUiState,
            )
        }
    }
}



@Composable
fun LoginHead(){
    Box(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
    ){
        Image(painter = painterResource(id = R.drawable.playascamana),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(bottomStart = 150.dp, bottomEnd = 150.dp)),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            logoPeru()
        }
    }
}

@Composable
fun LoginForm(
    onNavToHomePage: () -> Unit,
    loginViewModel: AuthViewModel = viewModel(),
    loginUiState: LoginUiState?,
){

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            loginViewModel.signWithCredential(credential)
        } catch (e: ApiException) {
            Log.w("TAG", "Google sign in failed", e)
        }
    }

    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)

    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(start = 5.dp, end = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido", fontSize = 25.sp, color = Primary)

        Surface(
            modifier = Modifier
                .clickable(
                    onClick = {
                        val gso = GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(token)
                            .requestEmail()
                            .build()

                        val googleSignInClient = GoogleSignIn.getClient(context, gso)
                        launcher.launch(googleSignInClient.signInIntent)
                    }
                )
                .fillMaxWidth(),
            border = BorderStroke(width = 1.dp, color = Color.LightGray),
            color = Color.White,
            shape = RoundedCornerShape(20.dp),
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
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_google),
                    contentDescription = null )
                Text(text = "Iniciar sesion con Google")
            }
        }

        if(loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }
        LaunchedEffect(key1 = loginViewModel?.hasUser){
            if(loginViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewLogin(){
    val loginViewModel: AuthViewModel? = null
    //LoginScreen(loginViewModel,{},{})
}