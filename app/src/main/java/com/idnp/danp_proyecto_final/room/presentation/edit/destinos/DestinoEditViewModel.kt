package com.idnp.danp_proyecto_final.room.presentation.edit.destinos

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.domain.use_cases.GetDepartamento
import com.idnp.danp_proyecto_final.room.domain.use_cases.GetDestino
import com.idnp.danp_proyecto_final.room.domain.use_cases.InsertDepartamento
import com.idnp.danp_proyecto_final.room.domain.use_cases.InsertDestino
import com.idnp.danp_proyecto_final.room.presentation.edit.DoubleFieldState
import com.idnp.danp_proyecto_final.room.presentation.edit.EditEvent
import com.idnp.danp_proyecto_final.room.presentation.edit.ImageState
import com.idnp.danp_proyecto_final.room.presentation.edit.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DestinoEditViewModel @Inject constructor(
    private val getDestino: GetDestino,
    private val insertDestino: InsertDestino,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _destinoTitle = mutableStateOf(TextFieldState())
    val destinoTitle: State<TextFieldState> = _destinoTitle

    private val _destinoDescripcion = mutableStateOf(TextFieldState())
    val destinoDescription: State<TextFieldState> = _destinoDescripcion

    private val _destinoImage = mutableStateOf(ImageState())
    val destinoImage: State<ImageState> = _destinoImage

    private val _destinoCategory = mutableStateOf(TextFieldState())
    val destinoCategory: State<TextFieldState> = _destinoCategory

    private val _destinoLatitud = mutableStateOf(DoubleFieldState())
    val destinoLatitud: State<DoubleFieldState> = _destinoLatitud

    private val _destinoLongitud = mutableStateOf(DoubleFieldState())
    val destinoLongitud: State<DoubleFieldState> = _destinoLongitud

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentDestinoId: Int? = null
    val codeDep:Int? = savedStateHandle.get<Int>("departamentoId")

    init {
        savedStateHandle.get<Int>("destinoId")?.let { destinoId ->
            if (destinoId != -1) {
                viewModelScope.launch {
                    getDestino(destinoId)?.also { destino ->
                        currentDestinoId = destino.id
                        _destinoTitle.value = destinoTitle.value.copy(
                            text = destino.title
                        )
                        _destinoDescripcion.value = destinoDescription.value.copy(
                            text = destino.description
                        )
                        _destinoImage.value = destinoImage.value.copy(
                            img = destino.image.toUri()
                        )
                        _destinoCategory.value = destinoCategory.value.copy(
                            text = destino.category
                        )
                        _destinoLatitud.value = destinoLatitud.value.copy(
                            text = destino.latitud
                        )
                        _destinoLongitud.value = destinoLongitud.value.copy(
                            text = destino.longitud
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: DestinoEditEvent) {
        when (event) {
            is DestinoEditEvent.EnteredTitle -> {
                _destinoTitle.value = destinoTitle.value.copy(
                    text = event.value
                )
            }
            is DestinoEditEvent.EnteredDescription -> {
                _destinoDescripcion.value = destinoDescription.value.copy(
                    text = event.value
                )
            }
            is DestinoEditEvent.EnteredImage -> {
                _destinoImage.value = destinoImage.value.copy(
                    img = event.value.toUri()
                )
            }
            is DestinoEditEvent.EnteredCategory -> {
                _destinoCategory.value = destinoCategory.value.copy(
                    text = event.value
                )
            }
            is DestinoEditEvent.EnteredLatitud -> {
                _destinoLatitud.value = destinoLatitud.value.copy(
                    text = event.value
                )
            }
            is DestinoEditEvent.EnteredLongitud -> {
                _destinoLongitud.value = destinoLongitud.value.copy(
                    text = event.value
                )
            }
            DestinoEditEvent.InsertDestino -> {
                viewModelScope.launch {
                    insertDestino(
                        Destino(
                            id = currentDestinoId,
                            codeDep = codeDep,
                            title = destinoTitle.value.text,
                            description = destinoDescription.value.text,
                            image = destinoImage.value.img.toString(),
                            category = destinoCategory.value.text,
                            latitud = destinoLatitud.value.text,
                            longitud = destinoLongitud.value.text
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveDestino)
                }
            }
        }
    }

    sealed class UiEvent {
        object SaveDestino: UiEvent()
    }
}