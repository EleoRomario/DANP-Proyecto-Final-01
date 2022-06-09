package com.idnp.danp_proyecto_final.room.presentation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Edit: Screen("edit?departamentoId={departamentoId}") {
        fun passId(departamentoId: Int?): String {
            return "edit?departamentoId=$departamentoId"
        }
    }
}
