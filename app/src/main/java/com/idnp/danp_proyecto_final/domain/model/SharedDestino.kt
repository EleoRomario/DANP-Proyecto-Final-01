package com.idnp.danp_proyecto_final.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SharedDestino (
    val departamento: String,
    val title: String,
    val description: String,
    val image: String,
    val latitud: String,
    val longitud: String,
    val category: String
    ): Parcelable