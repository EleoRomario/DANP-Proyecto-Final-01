package com.idnp.danp_proyecto_final.domain.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.idnp.danp_proyecto_final.data.models.Departamento
import com.idnp.danp_proyecto_final.data.models.Destino
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DepartamentosRepository
@Inject
constructor(
    private val firestore: FirebaseFirestore,
    private val departamentosList: CollectionReference
){
    fun getDepartamentosList(): Flow<Result<List<Departamento>>> = flow {
        try {
            emit(Result.Loading<List<Departamento>>())
            val departamentoList = departamentosList.get().await().map { document ->
               // val destino = firestore.collection("departamentos/${document.id}/destinos")
                Departamento(
                    id = document.id,
                    created = document.data.get("created").toString(),
                    title = document.data.get("title").toString(),
                    description = document.data.get("description").toString(),
                    image = document.data.get("image").toString(),
                )
                //document.toObject(Departamento::class.java)
            }
            Log.d("DESTINO", "-"+departamentoList)
            emit(Result.Success<List<Departamento>>(data = departamentoList))
        }catch (e: Exception){
            emit(Result.Error<List<Departamento>>(message = e.localizedMessage ?: "Error Desconocido"))
        }
    }

    fun getDestinosList(document: String):Flow<Result<List<Destino>>> = flow{
        try {
                emit(Result.Loading<List<Destino>>())
                //val destino = firestore.collection("departamentos/${document.id}/destinos")
                val destino = firestore.collection("departamentos").document("${document}").collection("destinos")

                val destinoList = destino.get().await().map { destino ->
                    //destino.toObject(Destino::class.java)
                    Destino(
                        destino.id,
                        destino.data.get("title").toString(),
                        destino.data.get("description").toString(),
                        destino.data.get("image").toString(),
                        destino.data.get("created").toString(),
                        destino.data.get("latitud").toString(),
                        destino.data.get("longitud").toString(),
                        destino.data.get("category").toString()
                    )
                }
            Log.d("DESTINOS", "-->"+destinoList)
            emit(Result.Success<List<Destino>>(data = destinoList))
        }catch (e: Exception){
            emit(Result.Error<List<Destino>>(message = e.localizedMessage ?: "Error Desconocido"))
        }
    }
}
