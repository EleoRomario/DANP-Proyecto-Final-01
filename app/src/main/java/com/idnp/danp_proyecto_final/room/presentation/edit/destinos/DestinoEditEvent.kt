package com.idnp.danp_proyecto_final.room.presentation.edit.destinos


sealed class DestinoEditEvent {
    data class EnteredTitle(val value: String): DestinoEditEvent()
    data class EnteredDescription(val value: String): DestinoEditEvent()
    data class EnteredImage(val value: String): DestinoEditEvent()
    data class EnteredCategory(val value: String): DestinoEditEvent()
    data class EnteredLatitud(val value: String): DestinoEditEvent()
    data class EnteredLongitud(val value: String): DestinoEditEvent()
    object InsertDestino: DestinoEditEvent()
}
