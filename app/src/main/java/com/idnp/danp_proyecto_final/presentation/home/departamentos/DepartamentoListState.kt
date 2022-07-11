package com.idnp.danp_proyecto_final.presentation.home.departamentos

import com.idnp.danp_proyecto_final.data.models.Departamento

data class DepartamentoListState(
    val isLoading: Boolean = false,
    val departamentos: List<Departamento> = emptyList(),
    val error: String = ""
)
