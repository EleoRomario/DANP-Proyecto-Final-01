package com.idnp.danp_proyecto_final.room.domain.model

import androidx.annotation.NonNull
import androidx.room.*

@Entity
data class Destino (
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "codeDep")@NonNull val codeDep:Int?,
    val title: String,
    val description: String,
    val image: String,
    val latitud: String,
    val longitud: String,
    val category: String
)
