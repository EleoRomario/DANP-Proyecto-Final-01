package com.idnp.danp_proyecto_final.room.data.source.local.dao

import androidx.room.*
import com.idnp.danp_proyecto_final.room.domain.model.Destino
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinoDao {
    @Query("SELECT * FROM Destino")
    fun getDestinos(): Flow<List<Destino>>

    @Query("SELECT * FROM Destino WHERE id = :id")
    suspend fun getDestinosById(id: Int): Destino?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDestino(destino: Destino)

    @Delete
    suspend fun deleteDestino(destino: Destino)
}