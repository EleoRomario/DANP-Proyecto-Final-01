package com.idnp.danp_proyecto_final.room.domain.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Departamento(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val title:String,
    val description: String,
    var image: String

)
