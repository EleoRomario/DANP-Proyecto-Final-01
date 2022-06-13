package com.idnp.danp_proyecto_final.room.presentation.home.destinos

import com.idnp.danp_proyecto_final.room.domain.model.Destino

sealed class DestinoEvent {
    data class DeleteDestino(val destino: Destino): DestinoEvent()
}