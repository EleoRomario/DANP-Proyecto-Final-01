package com.idnp.danp_proyecto_final.data.models

data class Destino(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val created: String,
    val latitud: String,
    val longitud: String,
    val category: String
){
    constructor() : this("","","","","","","","")
}
