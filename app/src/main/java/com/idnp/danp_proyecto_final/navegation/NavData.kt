package com.idnp.danp_proyecto_final.navegation

import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.DepartamentosData

data class NavData (
    val title:String,
    val icon:Int,
    val route: String,
    //val destino: List<DestinosData>,

    /**
     * Departamentos / Destinos List
     * */
)
val navList: List<NavData> = listOf(
    NavData(
        "home",
        R.drawable.ic_home,
        "AppScreens.ListDepartamentos.route",
    ),
    NavData(
        "destinos",
        R.drawable.ic_compass,
        "AppScreens.GridDepartamentos.route",
    ),
    NavData(
        "search",
        R.drawable.ic_search,
        "AppScreens.GridDepartamentos.route",
    ),
)
