package com.idnp.danp_proyecto_final.room.presentation.home.destinos


import com.idnp.danp_proyecto_final.room.domain.model.Destino

data class DestinoState (
    val destinos: List<Destino> = emptyList()
)