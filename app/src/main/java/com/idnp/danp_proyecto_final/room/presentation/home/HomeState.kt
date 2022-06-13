package com.idnp.danp_proyecto_final.room.presentation.home

import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.relation.DepartamentoWithDestinos

data class HomeState(
    //val departamentos: List<Departamento> = emptyList()
    val departamentos: List<DepartamentoWithDestinos> = emptyList()
)
