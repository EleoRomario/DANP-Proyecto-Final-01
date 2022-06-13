package com.idnp.danp_proyecto_final.room.domain.use_cases

import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.domain.repository.DestinoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDestinos @Inject constructor(
    private val repository: DestinoRepository
) {
    operator fun invoke(id: Int): Flow<List<Destino>> {
        return  repository.getDestinos(id)
    }
}