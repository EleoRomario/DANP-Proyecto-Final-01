package com.idnp.danp_proyecto_final.presentation.login.user

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.navegation.AppScreens
import com.idnp.danp_proyecto_final.presentation.logoPeru
import com.idnp.danp_proyecto_final.ui.theme.ColorLink
import com.idnp.danp_proyecto_final.ui.theme.Primary
import com.idnp.danp_proyecto_final.utils.LoadingState

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(), navController: NavController) {

    var emailState by remember {
        mutableStateOf("")
    }
    var passwordState by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    var isPassVisible by remember {
        mutableStateOf(false)
    }

    var isClicked = remember {
        mutableStateOf(false)
    }

    val state by viewModel.loadingState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signInUserUsingGoogle(credentials, navController)
        } catch (exception: Exception) {
            Log.d("Firebase Login", "LoginScreen: ${exception.localizedMessage}")
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginHead()
        Text(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 12.dp),
            text = "Bienvenido",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            color = Color.Black
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){

            OutlinedTextField(
                value = emailState,
                onValueChange = { emailState = it },
                placeholder = { Text(text = "user@email.com")},
                label = { Text("Email")},
                shape = RoundedCornerShape(20.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions( onNext = { focusManager.moveFocus(FocusDirection.Down)}),
                trailingIcon = {
                    if(emailState != ""){
                        IconButton(onClick = { emailState="" }) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                        }
                    }
                }
            )

            OutlinedTextField(
                value = passwordState,
                onValueChange =  { passwordState = it.take(8)},
                placeholder = { Text(text = "password") },
                label = { Text(text = "Password") },
                shape = RoundedCornerShape(20.dp),
                visualTransformation = if(isPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                trailingIcon = {
                    IconButton(onClick = { isPassVisible = !isPassVisible }) {
                        Icon(
                            imageVector = if (isPassVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null
                        )
                    }
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.70f)
                    .height(40.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.DarkGray,
                    contentColor = Color.White
                ),
                enabled = emailState.isNotEmpty() && passwordState.isNotEmpty(),
                onClick = {
                        viewModel.signInWithEmailAndPassword(
                            email = emailState.trim(),
                            password = passwordState.trim(),
                            navController = navController
                        )

                }) {
                Text(text = "Iniciar Sesion")
            }
            if (viewModel.loadingState.collectAsState().value == LoadingState.LOADING)
                CircularProgressIndicator(
                    modifier = Modifier.size(36.dp),
                    color = Primary
                )

            Text("o")

            val context = LocalContext.current
            val token = stringResource(R.string.default_web_client_id)

            GoogleSignInButton(isClicked = (viewModel.loadingState.collectAsState().value == LoadingState.LOADING)) {

                Log.d("Firebase Login", "LaunchingNavigation: Clickedd")
                val gso = GoogleSignInOptions.Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN
                )
                    .requestIdToken(token)
                    .requestEmail()
                    .build()

                val googleSIgnInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSIgnInClient.signInIntent)
            }

            Row() {
                Text(text = "¿No tienes cuenta?")
                Spacer(Modifier.width(10.dp))
                ClickableText(
                        text = AnnotatedString("Registrate"),
                        style = TextStyle(color = ColorLink),
                        onClick = {
                            navController.navigate(AppScreens.SignUpScreen.route)
                        }
                )
            }
        }
    }
}

/*@Composable
fun LoginForm(
    loginViewModel: LoginViewModel? = viewModel(),
    loginUiState: LoginUiState?,
    onNavToHomePage: () -> Unit
){

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            loginViewModel?.signWithCredential(credential)
        } catch (e: ApiException) {
            Log.w("LOGIN", "Google sign in failed", e)
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
                Text("Iniciar sesion con Google")

            }
        }

        if(loginUiState?.isLoading == true){
            Log.d("LOGIN","-${loginUiState.isLoading}")
            CircularProgressIndicator()
        }
        LaunchedEffect(key1 = loginViewModel?.hasUser){
            Log.d("LOGIN","->>>${loginViewModel?.hasUser}")
            if(loginViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }
    }
}*/
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
fun GoogleSignInButton(
    isClicked : Boolean,
    onClick: () -> Unit
) {


    OutlinedButton(
        modifier = Modifier
            .height(40.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 16.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick) {
        Row(modifier = Modifier
            .animateContentSize (
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )) {
            Icon(
                modifier = Modifier
                    .size(18.dp),
                tint = Color.Unspecified,
                painter = painterResource(R.drawable.ic_google),
                contentDescription = "google icon" )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Sign In with Google",
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(8.dp))
            if(isClicked)
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    color = Color(0xFF03A9F4),
                    strokeWidth = 2.5.dp
                )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewLogin(){
    val viewModel = LoginViewModel()
    val navController = rememberNavController()
    LoginScreen(viewModel, navController)
}