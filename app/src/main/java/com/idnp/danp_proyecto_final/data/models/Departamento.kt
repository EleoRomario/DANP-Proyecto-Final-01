package com.idnp.danp_proyecto_final.data.models


data class Departamento(
    val id: String,
    val created: String,
    val title: String,
    val description: String,
    val image: String,
){
    constructor() : this("","","","","")
}

