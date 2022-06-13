package com.idnp.danp_proyecto_final.room.domain.use_cases

import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.domain.repository.DestinoRepository
import javax.inject.Inject

class DeleteDestino @Inject constructor(
    private val repository: DestinoRepository
) {
    suspend operator fun invoke(destino: Destino) {
        repository.deleteDestino(destino)
    }
}