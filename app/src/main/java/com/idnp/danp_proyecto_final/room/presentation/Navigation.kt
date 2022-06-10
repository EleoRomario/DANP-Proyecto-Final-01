package com.idnp.danp_proyecto_final.room.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.idnp.danp_proyecto_final.room.presentation.edit.EditScreen
import com.idnp.danp_proyecto_final.room.presentation.home.HomeScreen
import com.idnp.danp_proyecto_final.room.presentation.home.destinos.DestinoScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Edit.route,
            arguments = listOf(
                navArgument(
                    name = "departamentoId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            EditScreen(navController = navController)
        }
        
        composable(route = Screen.DestinoHome.route){
            DestinoScreen(navController = navController)
        }
    }
}