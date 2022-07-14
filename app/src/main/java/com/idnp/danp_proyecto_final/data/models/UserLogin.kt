package com.idnp.danp_proyecto_final.data.models

data class UserLogin(
    val active: Boolean,
    val name: String,
    val photo: String,
    val email: String,
    val token: String,
    val created: String
) {
    constructor() : this(false,"","","","","")
}