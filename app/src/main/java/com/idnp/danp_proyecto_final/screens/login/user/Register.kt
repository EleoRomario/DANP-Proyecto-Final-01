package com.idnp.danp_proyecto_final.screens.login.user

import android.util.Patterns
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.ui.theme.ColorLink
import com.idnp.danp_proyecto_final.ui.theme.Primary
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterScreen(navController: NavController){
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LoginHead()
            Spacer(modifier = Modifier.height(10.dp))
            registerForm(navController)
        }
    }
}
@Composable
fun registerForm(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
){

    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    val isPassValid by derivedStateOf {
        pass.length > 7
    }
    var isPassVisible by remember {
        mutableStateOf(false)
    }

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(start = 5.dp, end = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registrarte", fontSize = 25.sp, color = Primary)

        OutlinedTextField(
            value = email,
            placeholder = { Text(text = "user@email.com") },
            label = { Text(text = "Email") },
            onValueChange = { email = it },
            shape = RoundedCornerShape(20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            isError = !isEmailValid,
            trailingIcon = {
                if(email.isNotBlank()){
                    IconButton(onClick = {email = ""}){
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                    }
                }
            }
        )
        if(email.isNotBlank() && !isEmailValid){
            Text(text = "Ingrese un email correcto", color = Color.Red)
        }

        OutlinedTextField(
            value = pass,
            placeholder = { Text(text = "password") },
            label = { Text(text = "Password") },
            onValueChange = { pass = it },
            shape = RoundedCornerShape(20.dp),
            visualTransformation = if(isPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            isError = !isPassValid,
            trailingIcon = {
                IconButton(onClick = { isPassVisible = !isPassVisible }) {
                    Icon(
                        imageVector = if (isPassVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        )
        if(pass.isNotBlank() && !isPassValid){
            Text("Contraseña mayor a 7 dígitos",color = Color.Red)
        }
        Button(
            enabled = isEmailValid && isPassValid,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
            onClick = {
                viewModel.register(email,pass)
            }) {
            when(state.status) {
                LoadingState.Status.IDLE -> {
                    Text(text = "Registrarte", color = Color.White, modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 16.dp,
                            top = 10.dp,
                            bottom = 10.dp
                        )
                    )
                }
                LoadingState.Status.RUNNING -> {
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
                LoadingState.Status.SUCCESS -> {
                    Text(text = "Usuario Registrado", color = Color.White, modifier = Modifier
                        .padding(
                            start = 12.dp,
                            end = 16.dp,
                            top = 10.dp,
                            bottom = 10.dp
                        )
                    )
                }
                LoadingState.Status.FAILED -> {
                    Text(text = state.msg ?: "Error")
                }
            }
        }
        Text(text = "o")

        Surface(
            modifier = Modifier
                .clickable(
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
                    imageVector = ImageVector.vectorResource(id = com.idnp.danp_proyecto_final.R.drawable.ic_google),
                    contentDescription = null )
                Text(text = "Registrate con Google")
            }
        }

        Row() {
            Text(text = "¿Ya tienes cuenta?")
            Spacer(Modifier.width(10.dp))
            ClickableText(
                text = AnnotatedString("Inicia sesion"),
                style = TextStyle(color = ColorLink),
                onClick = {
                        navController.navigate(route = AppScreens.Login.route)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewRegister(){
    //RegisterScreen()
}