package com.idnp.danp_proyecto_final.room.data.repository

import com.idnp.danp_proyecto_final.room.data.source.local.dao.DestinoDao
import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.domain.repository.DestinoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DestinoRepositoryImpl @Inject constructor(
    private val dao: DestinoDao
) : DestinoRepository{
    override fun getDestinos(): Flow<List<Destino>> {
        return dao.getDestinos()
    }

    override suspend fun getDestinoById(id: Int): Destino? {
        return dao.getDestinosById(id)
    }

    override suspend fun insertDestino(destino: Destino) {
        dao.insertDestino(destino)
    }

    override suspend fun deleteDestino(destino: Destino) {
        dao.deleteDestino(destino)
    }
}