package com.idnp.danp_proyecto_final.room.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idnp.danp_proyecto_final.room.data.source.local.dao.DepartamentoDao
import com.idnp.danp_proyecto_final.room.data.source.local.dao.DestinoDao
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.domain.relation.DepartamentoWithDestinos

@Database(
    entities = [Departamento::class, Destino::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val departamentoDao: DepartamentoDao
    abstract val destinoDao: DestinoDao
}