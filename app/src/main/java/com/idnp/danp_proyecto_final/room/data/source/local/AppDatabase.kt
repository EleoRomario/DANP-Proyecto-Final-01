package com.idnp.danp_proyecto_final.room.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.idnp.danp_proyecto_final.room.data.source.local.dao.DepartamentoDao
import com.idnp.danp_proyecto_final.room.domain.model.Departamento

@Database(
    entities = [Departamento::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val departamentoDao: DepartamentoDao
}