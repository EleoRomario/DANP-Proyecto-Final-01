package com.idnp.danp_proyecto_final.room.presentation.edit.destinos

import android.net.Uri

sealed class DestinoEditEvent {
    data class EnteredTitle(val value: String): DestinoEditEvent()
    data class EnteredDescription(val value: String): DestinoEditEvent()
    data class EnteredImage(val value: Uri): DestinoEditEvent()
    data class EnteredCategory(val value: String): DestinoEditEvent()
    data class EnteredLatitud(val value: Double): DestinoEditEvent()
    data class EnteredLongitud(val value: Double): DestinoEditEvent()
    object InsertDestino: DestinoEditEvent()
}
