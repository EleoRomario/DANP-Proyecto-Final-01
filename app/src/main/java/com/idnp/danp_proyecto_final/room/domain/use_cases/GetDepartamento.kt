package com.idnp.danp_proyecto_final.room.domain.use_cases

import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.repository.DepartamentoRepository
import javax.inject.Inject

class GetDepartamento @Inject constructor(
    private val repository: DepartamentoRepository
) {
    suspend operator fun invoke(id: Int): Departamento? {
        return repository.getDepartamentoById(id)
    }
}