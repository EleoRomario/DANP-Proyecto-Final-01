package com.idnp.danp_proyecto_final.presentation.login.user

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.idnp.danp_proyecto_final.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    val currentUser = repository.currentUser

    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
    private set

    fun onUserEmailChange(emailUser: String){
        loginUiState = loginUiState.copy(emailUser = emailUser)
    }
    fun onUserPassChange(passUser: String){
        loginUiState = loginUiState.copy(passUser = passUser)
    }
    fun onEmailSignupChange(emailUser: String){
        loginUiState = loginUiState.copy(emailUserSignUp = emailUser)
    }
    fun onPassSignupChange(passUser: String){
        loginUiState = loginUiState.copy(passUserSignup = passUser)
    }
    fun onConfirmPassChange(passUser: String){
        loginUiState = loginUiState.copy(confirmPassSignup = passUser)
    }

    private fun validateLoginForm() = loginUiState.emailUser.isNotBlank() && loginUiState.passUser.isNotBlank()
    private fun validateSignupForm() = loginUiState.emailUserSignUp.isNotBlank() && loginUiState.passUserSignup.isNotBlank() && loginUiState.confirmPassSignup.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try{
            if (!validateSignupForm()){
                throw IllegalArgumentException("Email y contraseña no deben estar vacios")
            }

            loginUiState = loginUiState.copy(isLoading = true)
            if(!Patterns.EMAIL_ADDRESS.matcher(loginUiState.emailUserSignUp).matches()){
                throw IllegalArgumentException(
                    "Ingrese email válido"
                )
            }

            if(loginUiState.passUserSignup.length < 7){
                throw IllegalArgumentException(
                    "Contraseña mínimo 7 carácteres"
                )
            }
            if(loginUiState.passUserSignup != loginUiState.confirmPassSignup){
                throw IllegalArgumentException(
                    "Contraseñas diferente"
                )
            }


            loginUiState = loginUiState.copy(signUpError = null)

            repository.createUser(
                loginUiState.emailUserSignUp,
                loginUiState.passUserSignup
            ){ isSuccessful ->
                Log.d("AUTH", "-> "+loginUiState.emailUserSignUp+" -- "+loginUiState.emailUserSignUp)
                if(isSuccessful){
                    Toast.makeText(
                        context,
                        "Registrado",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Failed Register",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }

        }catch (e: Exception){
            loginUiState = loginUiState.copy(signUpError =  e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try{
            if (!validateLoginForm()){
                throw IllegalArgumentException("Email y contraseña no deben estar vacios")
            }

            loginUiState = loginUiState.copy(isLoading = true)

            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.emailUser,
                loginUiState.passUser
            ){ isSuccessful ->
                if(isSuccessful){
                    Toast.makeText(
                        context,
                        "Logeado",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Failed Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }

        }catch (e: Exception){
            loginUiState = loginUiState.copy(loginError =  e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }

}

data class LoginUiState(
    val emailUser: String = "",
    val passUser: String = "",
    val emailUserSignUp:String = "",
    val passUserSignup: String = "",
    val confirmPassSignup: String = "",
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null
)