package com.idnp.danp_proyecto_final.data.models

import java.util.*

data class Departamento(
    val id: String,
    val created: Date,
    val title: String,
    val description: String,
    val image: String
){
    constructor() : this("",Date(),"","","")
}
