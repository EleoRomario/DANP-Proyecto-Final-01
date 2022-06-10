package com.idnp.danp_proyecto_final.room.domain.repository

import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import kotlinx.coroutines.flow.Flow

interface DepartamentoRepository {
    fun getDepartamentos(): Flow<List<Departamento>>

    suspend fun getDepartamentoById(id: Int): Departamento?

    suspend fun insertDepartamento(departamento: Departamento)

    suspend fun deleteDepartamento(departamento: Departamento)
}