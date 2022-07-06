package com.idnp.danp_proyecto_final.presentation.login.user

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import com.idnp.danp_proyecto_final.presentation.logoPeru
import com.idnp.danp_proyecto_final.ui.theme.ColorLink
import com.idnp.danp_proyecto_final.ui.theme.Primary

@Composable
fun LoginScreen(
    loginViewModel: AuthViewModel? = null,
    onNavToHomePage: () -> Unit,
    onNavToSignupPage: () ->Unit
){

    val loginUiState = loginViewModel?.loginUiState

    val isError = loginUiState?.loginError != null

    val context = LocalContext.current


    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoginHead()
            Spacer(modifier = Modifier.height(10.dp))
            LoginForm(
                onNavToHomePage,
                onNavToSignupPage,
                context,
                loginViewModel,
                loginUiState,
                isError
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
    onNavToSignupPage: () ->Unit,
    context: Context,
    loginViewModel: AuthViewModel?,
    loginUiState: LoginUiState?,
    isError: Boolean
){
    val focusManager = LocalFocusManager.current

    var isPassVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(start = 5.dp, end = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido", fontSize = 25.sp, color = Primary)

        if(isError){
            Text(text = loginUiState?.loginError ?: "", color = Color.Red)
        }
        
        OutlinedTextField(
            value = loginUiState?.emailUser ?: "",
            placeholder = { Text(text = "user@email.com") },
            label = { Text(text = "Email") },
            onValueChange = { loginViewModel?.onUserEmailChange(it) },
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            isError = isError,
            trailingIcon = {
                if(loginUiState?.emailUser != ""){
                    IconButton(onClick = {}){
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                    }
                }
            }
        )

        OutlinedTextField(
            value = loginUiState?.passUser ?: "",
            placeholder = { Text(text = "password") },
            label = { Text(text = "Password") },
            onValueChange = { loginViewModel?.onUserPassChange(it)},
            shape = RoundedCornerShape(20.dp),
            visualTransformation = if(isPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            isError = isError,
            trailingIcon = {
                IconButton(onClick = { isPassVisible = !isPassVisible }) {
                    Icon(
                        imageVector = if (isPassVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
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
            onClick = {
                loginViewModel?.loginUser(context)
            }) {
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
            ClickableText(
                text = AnnotatedString("Registrate"),
                style = TextStyle(color = ColorLink),
                onClick = {
                    onNavToSignupPage.invoke()
                }
            )
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
    val navController: NavController
    //LoginScreen(navController)
}