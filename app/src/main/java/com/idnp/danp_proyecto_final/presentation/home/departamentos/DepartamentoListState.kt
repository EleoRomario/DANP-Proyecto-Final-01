package com.idnp.danp_proyecto_final.presentation.home.departamentos

import com.idnp.danp_proyecto_final.data.models.Departamento
import com.idnp.danp_proyecto_final.data.models.Destino

data class DepartamentoListState(
    val isLoading: Boolean = false,
    val departamentos: List<Departamento> = emptyList(),
    val error: String = ""
)
data class DestinoListState(
    val isLoading: Boolean = false,
    val destinos: List<Destino> = emptyList(),
    val error: String = ""
)
