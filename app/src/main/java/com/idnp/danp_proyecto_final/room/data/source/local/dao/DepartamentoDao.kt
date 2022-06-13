package com.idnp.danp_proyecto_final.room.data.source.local.dao

import androidx.room.*
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.relation.DepartamentoWithDestinos
import kotlinx.coroutines.flow.Flow

@Dao
interface DepartamentoDao {
    @Query("SELECT * FROM Departamento")
    fun getDepartamentos(): Flow<List<Departamento>>

    @Transaction
    @Query("SELECT * FROM Departamento")
    fun getDestinosWithDepartamentos(): Flow<List<DepartamentoWithDestinos>>

    @Query("SELECT * FROM Departamento WHERE id = :id")
    suspend fun getDepartamentoById(id: Int): Departamento?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDepartamento(departamento: Departamento)

    @Delete
    suspend fun deleteDepartamento(departamento: Departamento)


}