package com.idnp.danp_proyecto_final.navegation

sealed class AppScreens(val route:String){

    object Inicio: AppScreens("inicio_screen")
    object Login: AppScreens("login_screen")
    object Register: AppScreens("register_screen")

    object HomeDepartamentos: AppScreens("home_departamentos_screen")
    object ListDepartamentos: AppScreens("list_departamentos_screen")
    object CategoryDestinos: AppScreens("category_destinos_screen")
    object DetalleDepartamento: AppScreens("detalle_departamento_screen")
    object ListLugaresTuristico: AppScreens("list_lugares_turistico_screen")
    object DetalleLugarTuristico: AppScreens("detalle_lugar_turistico_screen")
    object SearchDestino: AppScreens("search_destino_screen")
}
