package com.idnp.danp_proyecto_final.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.idnp.danp_proyecto_final.data.models.Departamento
import com.idnp.danp_proyecto_final.domain.repository.DepartamentosRepositoy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class DepartamentosRepositoryImpl
@Inject
constructor(
    private val source: FirestorePagingSource,
    private val config: PagingConfig
): DepartamentosRepositoy
{
    override fun getProducts() = Pager(
        config = config
    ){
        source
    }.flow
}