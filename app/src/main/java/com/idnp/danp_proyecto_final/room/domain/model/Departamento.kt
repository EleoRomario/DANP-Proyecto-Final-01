package com.idnp.danp_proyecto_final.room.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Departamento(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val code:String,
    val title:String,
    val imgUri:Int,
    val description: String,
)
