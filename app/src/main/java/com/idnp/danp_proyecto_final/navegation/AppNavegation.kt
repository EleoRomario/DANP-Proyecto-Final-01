package com.idnp.danp_proyecto_final.navegation

import SearchDestinosScreen
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import com.idnp.danp_proyecto_final.data.models.Departamento
import com.idnp.danp_proyecto_final.data.models.Destino
import com.idnp.danp_proyecto_final.domain.model.SharedDestino
import com.idnp.danp_proyecto_final.domain.viewsmodel.SharedViewModel
import com.idnp.danp_proyecto_final.presentation.*
import com.idnp.danp_proyecto_final.presentation.home.HomeScreen
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DepartamentoListState
import com.idnp.danp_proyecto_final.presentation.home.departamentos.DetalleDepartamentoScreen
import com.idnp.danp_proyecto_final.presentation.home.departamentos.ListDepartamentosScreen
import com.idnp.danp_proyecto_final.presentation.home.destinos.CategoryDestinosScreen
import com.idnp.danp_proyecto_final.presentation.home.destinos.DetalleLugarTuristicoScreen
import com.idnp.danp_proyecto_final.presentation.home.destinos.ListLugaresTuristicoScreen
import com.idnp.danp_proyecto_final.presentation.login.user.LoginScreen
import com.idnp.danp_proyecto_final.presentation.profile.ProfileScreen

@Composable
fun AppNavigation(
    state: DepartamentoListState,
    user: FirebaseUser?
)
{

    val navController = rememberNavController()

    val context = LocalContext.current

    val uri = "app://com.idnap.danp_proyecto_final/"

    val actions = remember(navController){
        Actions(navController, context)
    }


    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
        /**
        if(user == null)
            AppScreens.LoginScreen.route
        else
            AppScreens.HomeScreen.route**/
    ){
        composable( route = AppScreens.SplashScreen.route){
            SplashScreen(state, navController, user)
        }
        composable( route = AppScreens.LoginScreen.route ){
            LoginScreen(navController = navController)
        }
        composable( route = AppScreens.HomeScreen.route
        ) {
            HomeScreen(state,navController)
        }
        composable(
            route = AppScreens.ListDepartamentos.route
        ){
            ListDepartamentosScreen(state, navController)
        }

        composable(route = AppScreens.DetalleDepartamento.route + "/{departamento}", arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            })){
            DetalleDepartamentoScreen(state, navController,it.arguments?.getString("departamento"))
        }

        composable(route = AppScreens.CategoryDestinos.route + "/{category}", arguments = listOf(
            navArgument(name = "category"){
                type = NavType.StringType
            })){
            CategoryDestinosScreen(state, navController, it.arguments?.getString("category"))
        }



        composable(route = AppScreens.ListLugaresTuristico.route + "/{departamento}" , arguments = listOf(
            navArgument(name = "departamento"){
                type = NavType.StringType
            },
        )){
            ListLugaresTuristicoScreen(
                state = state,
                navController = navController,
                it.arguments?.getString("departamento")
            )
        }

       /* composable(route = AppScreens.DetalleLugarTuristico.route + "/{departamento}/{title}/{description}/{image}/{latitud}/{longitud}",
            arguments = listOf(
                navArgument(name = "departamento"){
                    type = NavType.StringType
                },
                navArgument(name = "title"){
                    type = NavType.StringType
                },
                navArgument(name = "description"){
                    type = NavType.StringType
                },
                navArgument(name = "image"){
                    type = NavType.StringType
                },
                navArgument(name = "latitud"){
                    type = NavType.StringType
                },
                navArgument(name = "longitud"){
                    type = NavType.StringType
                },
            )
        ){
            DetalleLugarTuristicoScreen(
                navController,
                it.arguments?.getString("departamento"),
                it.arguments?.getString("title"),
                it.arguments?.getString("description"),
                it.arguments?.getString("image"),
                it.arguments?.getString("latitud"),
                it.arguments?.getString("longitud"),
            )
        }*/

        composable(route = AppScreens.DetalleLugarTuristico.route){
            var destino = navController.previousBackStackEntry?.arguments?.getParcelable<SharedDestino>("shared_destino")
            destino?.let {
                DetalleLugarTuristicoScreen(
                    destino = it,
                    navController = navController,
                )
            }
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