package com.idnp.danp_proyecto_final.room.presentation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Edit: Screen("edit?departamentoId={departamentoId}") {
        fun passId(departamentoId: Int?): String {
            return "edit?departamentoId=$departamentoId"
        }
    }
    object DestinoHome: Screen("destinos?departamentoId={departamentoId}"){
        fun passId(departamentoId: Int?): String {
            return "destinos?departamentoId=$departamentoId"
        }
    }
    object DestinoEdit: Screen("destinoedit?departamentoId={departamentoId}?destinoId={destinoId}"){
        fun passId(departamentoId: Int?, destinoId: Int?): String{
            return "destinoedit?departamentoId=$departamentoId?destinoId=$destinoId"
        }
    }
}
