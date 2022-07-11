package com.idnp.danp_proyecto_final.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.idnp.danp_proyecto_final.data.models.Departamento
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DepartamentosRepository
@Inject
constructor(
    private val departamentosList: CollectionReference
){
    fun getDepartamentosList(): Flow<Result<List<Departamento>>> = flow {
        try {
            emit(Result.Loading<List<Departamento>>())
            val departamentoList = departamentosList.get().await().map { document ->
                document.toObject(Departamento::class.java)
            }
            emit(Result.Success<List<Departamento>>(data = departamentoList))
        }catch (e: Exception){
            emit(Result.Error<List<Departamento>>(message = e.localizedMessage ?: "Error Desconocido"))
        }
    }
}
