package com.idnp.danp_proyecto_final.room.presentation.home

import com.idnp.danp_proyecto_final.room.domain.model.Departamento

sealed class HomeEvent {
    data class Deletedepartamento(val departamento: Departamento): HomeEvent()
}
