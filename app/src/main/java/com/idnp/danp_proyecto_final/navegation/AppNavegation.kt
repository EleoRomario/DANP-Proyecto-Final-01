package com.idnp.danp_proyecto_final.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.idnp.danp_proyecto_final.screens.*


@Composable
fun AppNavigation (){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeDepartamentos.route
    ){

        composable(route = AppScreens.HomeDepartamentos.route ){
            HomeDepartamentosScreen(navController)
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
    }
}