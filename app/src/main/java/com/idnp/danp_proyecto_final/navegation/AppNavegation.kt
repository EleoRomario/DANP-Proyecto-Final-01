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
    NavHost(navController = navController, startDestination = AppScreens.ListDepartamentos.route){

        composable(route = AppScreens.ListDepartamentos.route ){
            ListDepartamentosScreen(navController)
        }

        composable(route = AppScreens.GridDepartamentos.route){
            GridDepartamentosScreen(navController)
            //GridDepartamentosScreen(navController, it.arguments?.getString("region"))
        }

        composable(route = AppScreens.DetalleDepartamento.route + "/{departamento}", arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            })){
            DetalleDepartamentoScreen(navController,it.arguments?.getString("departamento"))
        }

        composable(route = AppScreens.ListLugaresTuristico.route + "/{departamento}" + "/{destinos}", arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            },
            navArgument("destinos"){
                type = NavType.StringType
            },
            )){
            ListLugaresTuristicoScreen(navController)
        }

        composable(route = AppScreens.DetalleLugarTuristico.route + "/{departamento}" + "/{destinos}", arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            },
            navArgument("destinos"){
                type = NavType.StringType
            },
        )){
            DetalleLugarTuristicoScreen(navController)
        }
    }
}