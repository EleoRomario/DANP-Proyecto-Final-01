package com.idnp.danp_proyecto_final.room.domain.use_cases

import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.relation.DepartamentoWithDestinos
import com.idnp.danp_proyecto_final.room.domain.repository.DepartamentoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDepartamentoWithDestinos @Inject constructor(
    private val repository: DepartamentoRepository
) {
    operator fun invoke(): Flow<List<DepartamentoWithDestinos>> {
        return repository.getDestinosWithDepartamentos()
    }
}