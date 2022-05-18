package com.idnp.danp_proyecto_final.navegation

sealed class AppScreens(val route:String){
    object ListDepartamentos: AppScreens("list_departamentos_screen")
    object GridDepartamentos: AppScreens("grid_departamentos_screen")
    object DetalleDepartamento: AppScreens("detalle_departamento_screen")
    object ListLugaresTuristico: AppScreens("list_lugares_turistico_screen")
    object DetalleLugarTuristico: AppScreens("detalle_lugar_turistico_screen")
}
