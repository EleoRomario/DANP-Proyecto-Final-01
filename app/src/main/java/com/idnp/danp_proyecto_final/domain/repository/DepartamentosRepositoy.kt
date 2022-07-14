package com.idnp.danp_proyecto_final.domain.repository

import androidx.paging.PagingData
import com.idnp.danp_proyecto_final.data.models.Departamento
import kotlinx.coroutines.flow.Flow

interface DepartamentosRepositoy {
    fun getProducts(): Flow<PagingData<Departamento>>
}