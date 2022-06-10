package com.idnp.danp_proyecto_final.room.data.repository

import com.idnp.danp_proyecto_final.room.data.source.local.dao.DepartamentoDao
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.repository.DepartamentoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DepartamentoRepositoryImpl @Inject constructor(
    private val dao: DepartamentoDao
): DepartamentoRepository {
    override fun getDepartamentos(): Flow<List<Departamento>> {
        return dao.getDepartamentos()
    }

    override suspend fun getDepartamentoById(id: Int): Departamento? {
       return dao.getDepartamentoById(id)
    }

    override suspend fun insertDepartamento(departamento: Departamento) {
        dao.insertDepartamento(departamento)
    }

    override suspend fun deleteDepartamento(departamento: Departamento) {
        dao.deleteDepartamento(departamento)
    }

}