package com.idnp.danp_proyecto_final.room.presentation.edit

sealed class EditEvent {
    data class EnteredTitle(val value: String): EditEvent()
    data class EnteredDescription(val value: String): EditEvent()
    data class EnteredImage(val value: String): EditEvent()
    object InsertDepartamento: EditEvent()
}