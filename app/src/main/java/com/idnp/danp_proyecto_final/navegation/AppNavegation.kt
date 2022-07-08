package com.idnp.danp_proyecto_final.navegation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.firebase.auth.FirebaseUser
import com.idnp.danp_proyecto_final.presentation.*
import com.idnp.danp_proyecto_final.presentation.home.HomeScreen
import com.idnp.danp_proyecto_final.presentation.login.user.LoginScreen
import com.idnp.danp_proyecto_final.presentation.profile.ProfileScreen

@Composable
fun AppNavigation(user: FirebaseUser?) {

    val navController = rememberNavController()

    val context = LocalContext.current

    val uri = "app://com.idnap.danp_proyecto_final/"

    val actions = remember(navController){
        Actions(navController, context)
    }

    NavHost(navController = navController, startDestination = if(user == null)
        AppScreens.LoginScreen.route else AppScreens.HomeScreen.route){
        composable( route = AppScreens.LoginScreen.route ){
            LoginScreen(navController = navController)
        }
        composable( route = AppScreens.HomeScreen.route ) {
            HomeScreen(navController)
        }

        composable(
            route = AppScreens.ListDepartamentos.route,
            deepLinks = listOf(navDeepLink { uriPattern = "$uri" })
        ){
            ListDepartamentosScreen(navController)
        }

        composable(route = AppScreens.CategoryDestinos.route + "/{category}", arguments = listOf(
            navArgument(name = "category"){
                type = NavType.StringType
            })){
            CategoryDestinosScreen(navController, it.arguments?.getString("category"))
        }

        composable(route = AppScreens.DetalleDepartamento.route + "/{departamento}", arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            })){
            DetalleDepartamentoScreen(navController,it.arguments?.getString("departamento"))
        }

        composable(route = AppScreens.ListLugaresTuristico.route + "/{departamento}" , arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            },
        )){
            ListLugaresTuristicoScreen(navController,it.arguments?.getString("departamento"))
        }

        composable(route = AppScreens.DetalleLugarTuristico.route + "/{departamento}/{destino}",
            arguments = listOf(
                navArgument(name = "departamento"){
                    type = NavType.StringType
                },
                navArgument(name = "destino"){
                    type = NavType.StringType
                },
            )
        ){
            DetalleLugarTuristicoScreen(navController,it.arguments?.getString("departamento"),it.arguments?.getString("destino"))
        }

        composable(route = AppScreens.SearchDestino.route ){
            SearchDestinosScreen(navController)
        }

        composable(route = AppScreens.ProfileScreen.route ){
            ProfileScreen(navController)
        }

        /*
        composable( route = AppScreens.SignUpScreen.route ){
            SignUpScreen(navController = navController)
        }*/
    }
}
internal data class Actions(val navController: NavHostController, val context: Context) {


    val openDetail: () -> Unit = {
        navController.navigate(AppScreens.ListDepartamentos.route)
    }

}
        /*composable(route = LoginRoutes.Signup.name ){
            RegisterScreen(onNavToHomePage = {
                navController.navigate(HomeRoutes.Home.name){
                    popUpTo(LoginRoutes.Signup.name){
                        inclusive = true
                    }
                }
            },
                loginViewModel = AuthViewModel
            ){
                navController.navigate(LoginRoutes.SignIn.name)
            }
        }

        composable(route = HomeRoutes.Home.name){
            HomeScreen(LoginViewModel, onNavLogin = {
                navController.navigate(LoginRoutes.SignIn.name){
                    launchSingleTop = true
                }
            })
        }

        composable(route = AppScreens.HomeDepartamentos.route ){
            HomeScreen()
        }

        composable(route = AppScreens.ListDepartamentos.route){
            ListDepartamentosScreen(navController)
        }

        composable(route = AppScreens.CategoryDestinos.route + "/{category}", arguments = listOf(
            navArgument(name = "category"){
                type = NavType.StringType
            })){
            CategoryDestinosScreen(navController, it.arguments?.getString("category"))
        }

        composable(route = AppScreens.DetalleDepartamento.route + "/{departamento}", arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            })){
            DetalleDepartamentoScreen(navController,it.arguments?.getString("departamento"))
        }

        composable(route = AppScreens.ListLugaresTuristico.route + "/{departamento}" , arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            },
            )){
            ListLugaresTuristicoScreen(navController,it.arguments?.getString("departamento"))
        }

        composable(route = AppScreens.DetalleLugarTuristico.route + "/{departamento}/{destino}",
            arguments = listOf(
                navArgument(name = "departamento"){
                    type = NavType.StringType
                },
                navArgument(name = "destino"){
                    type = NavType.StringType
                },
            )
        ){
            DetalleLugarTuristicoScreen(navController,it.arguments?.getString("departamento"),it.arguments?.getString("destino"))
        }

        composable(route = AppScreens.SearchDestino.route ){
            SearchDestinosScreen(navController)
        }
    }
}*/