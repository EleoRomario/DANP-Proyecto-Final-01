package com.idnp.danp_proyecto_final.room.presentation.edit.destinos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idnp.danp_proyecto_final.room.domain.model.Destino
import com.idnp.danp_proyecto_final.room.domain.use_cases.GetDestino
import com.idnp.danp_proyecto_final.room.domain.use_cases.InsertDestino
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

    private val _destinoImage = mutableStateOf(TextFieldState())
    val destinoImage: State<TextFieldState> = _destinoImage

    private val _destinoCategory = mutableStateOf(TextFieldState())
    val destinoCategory: State<TextFieldState> = _destinoCategory

    private val _destinoLatitud = mutableStateOf(TextFieldState())
    val destinoLatitud: State<TextFieldState> = _destinoLatitud

    private val _destinoLongitud = mutableStateOf(TextFieldState())
    val destinoLongitud: State<TextFieldState> = _destinoLongitud
    private val _departamentoID = mutableStateOf(TextFieldState())
    val departamentoID: State<TextFieldState> = _departamentoID

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentDestinoId: Int? = null
    val codeDep:Int? = savedStateHandle.get<Int>("departamentoId")

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
                    text = event.value
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
            is DestinoEditEvent.EnteredDepID -> {
                _departamentoID.value = departamentoID.value.copy(
                    text = event.value
                )
            }

            DestinoEditEvent.InsertDestino -> {
                viewModelScope.launch {
                    viewModelScope.launch {
                        insertDestino(
                            Destino(
                                id = currentDestinoId,
                                codeDep = departamentoID.value.text.toInt(),
                                title = destinoTitle.value.text,
                                description = destinoDescription.value.text,
                                image = destinoImage.value.text,
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
    }

    sealed class UiEvent {
        object SaveDestino: UiEvent()
    }
}