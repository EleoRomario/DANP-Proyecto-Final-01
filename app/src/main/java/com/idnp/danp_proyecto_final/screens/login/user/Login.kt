package com.idnp.danp_proyecto_final.screens.login.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.screens.logoPeru
import com.idnp.danp_proyecto_final.ui.theme.ColorLink
import com.idnp.danp_proyecto_final.ui.theme.Primary
import androidx.compose.ui.graphics.Shape as Shape1

@Composable
fun LoginScreen(navController: NavController){
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoginHead()
            Spacer(modifier = Modifier.height(10.dp))
            LoginForm(navController)
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
fun LoginForm(navController: NavController){
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth
    auth = Firebase.auth

    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(start = 5.dp, end = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido", fontSize = 25.sp, color = Primary)

        OutlinedTextField(
            value = email,
            placeholder = { Text(text = "user@email.com") },
            label = { Text(text = "Email") },
            onValueChange = { email = it },
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        OutlinedTextField(
            value = pass,
            placeholder = { Text(text = "password") },
            label = { Text(text = "Password") },
            onValueChange = { pass = it },
            shape = RoundedCornerShape(20.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        Text("Olvide mi contraseña",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
            onClick = { /*TODO*/ }) {
            Text(text = "Iniciar Sesion", color = Color.White, modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 10.dp,
                    bottom = 10.dp
                ),)
        }
        Text(text = "o")
        
        Surface(
            modifier = Modifier
                .clickable(
                    //enabled = !isLoading,
                    onClick = { }
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

        Row() {
            Text(text = "¿No tienes cuenta?")
            Spacer(Modifier.width(10.dp))
            Text("Registrate ahora",color = ColorLink,
            modifier = Modifier.clickable(
                onClick = {
                    navController.navigate(route = AppScreens.Register.route)
                }
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewLogin(){
    val navController: NavController
    //LoginScreen(navController)
}