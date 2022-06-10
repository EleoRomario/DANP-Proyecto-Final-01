package com.idnp.danp_proyecto_final.room.domain.use_cases

import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.domain.repository.DestinoRepository
import javax.inject.Inject

class GetDestino @Inject constructor(
    private val repository: DestinoRepository
) {
    suspend operator fun invoke(id: Int): Destino? {
        return repository.getDestinoById(id)
    }
}