package com.idnp.danp_proyecto_final.room.presentation.home

import com.idnp.danp_proyecto_final.room.domain.model.Departamento

data class HomeState(
    val departamentos: List<Departamento> = emptyList()
)
