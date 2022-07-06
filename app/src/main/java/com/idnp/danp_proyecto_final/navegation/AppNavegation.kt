package com.idnp.danp_proyecto_final.navegation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.idnp.danp_proyecto_final.presentation.*
import com.idnp.danp_proyecto_final.presentation.home.HomeScreen
import com.idnp.danp_proyecto_final.presentation.login.user.AuthViewModel
import com.idnp.danp_proyecto_final.presentation.login.user.LoginScreen
//import com.idnp.danp_proyecto_final.presentation.login.user.RegisterScreen

enum class LoginRoutes{
    //Signup,
    SignIn
}
enum class HomeRoutes{
    Home
}


@Composable
fun AppNavigation (
    navController: NavHostController = rememberNavController(),
    AuthViewModel: AuthViewModel
){
    NavHost(
        navController = navController,
        startDestination = LoginRoutes.SignIn.name
    ){

        composable(route = LoginRoutes.SignIn.name ){
            LoginScreen(onNavToHomePage = {
                navController.navigate(HomeRoutes.Home.name){
                    launchSingleTop = true
                }
            },
                loginViewModel = AuthViewModel
            )
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
        }*/

        composable(route = HomeRoutes.Home.name){
            HomeScreen(AuthViewModel, onNavLogin = {
                navController.navigate(LoginRoutes.SignIn.name){
                    launchSingleTop = true
                }
            })
        }

















        /*composable(route = AppScreens.HomeDepartamentos.route ){
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
        }*/
    }
}