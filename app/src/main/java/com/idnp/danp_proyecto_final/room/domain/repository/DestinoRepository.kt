package com.idnp.danp_proyecto_final.room.domain.repository

import com.idnp.danp_proyecto_final.room.domain.model.Destino
import kotlinx.coroutines.flow.Flow

interface DestinoRepository {

    fun getDestinos(): Flow<List<Destino>>

    suspend fun getDestinoById(id: Int): Destino?

    suspend fun insertDestino(destino: Destino)

    suspend fun deleteDestino(destino: Destino)
}