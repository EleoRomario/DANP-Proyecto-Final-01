package com.idnp.danp_proyecto_final.navegation

import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.data.DepartamentosData

data class NavData(
    var id: Int,
    var title:String,
    var icon: Int,
    var route: String,
)
val navList: List<NavData> = listOf(
    NavData(
        0,
        "Home",
        R.drawable.ic_home,
        AppScreens.HomeScreen.route
    ),
    NavData(
        1,
        "List",
        R.drawable.ic_compass,
        AppScreens.ListDepartamentos.route
    ),
    NavData(
        2,
        "Categorias",
        R.drawable.ic_categori,
        AppScreens.CategoryDestinos.route + "/cultural"
    ),
    NavData(
        3,
        "Categorias",
        R.drawable.ic_search_menu,
        AppScreens.SearchDestino.route
    )
)