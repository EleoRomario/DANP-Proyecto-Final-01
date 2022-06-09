package com.idnp.danp_proyecto_final.room.presentation.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idnp.danp_proyecto_final.R
import com.idnp.danp_proyecto_final.room.domain.model.Departamento
import com.idnp.danp_proyecto_final.room.domain.use_cases.GetDepartamento
import com.idnp.danp_proyecto_final.room.domain.use_cases.InsertDepartamento
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getdepartamento: GetDepartamento,
    private val insertdepartamento: InsertDepartamento,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _departamentoTitle = mutableStateOf(TextFieldState())
    val departamentoTitle: State<TextFieldState> = _departamentoTitle

    private val _departamentoDescription = mutableStateOf(TextFieldState())
    val departamentoDescription: State<TextFieldState> = _departamentoDescription

    private val _departamentoCode = mutableStateOf(TextFieldState())
    val departamentoCode: State<TextFieldState> = _departamentoCode

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentDepartamentoId: Int? = null

    init {
        savedStateHandle.get<Int>("departamentoId")?.let { departamentoId ->
            if (departamentoId != -1) {
                viewModelScope.launch {
                    getdepartamento(departamentoId)?.also { departamento ->
                        currentDepartamentoId = departamento.id
                        _departamentoTitle.value = departamentoTitle.value.copy(
                            text = departamento.title
                        )
                        _departamentoCode.value = departamentoCode.value.copy(
                            text = departamento.code.toString()
                        )
                        _departamentoDescription.value = departamentoDescription.value.copy(
                            text = departamento.description
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: EditEvent) {
       when (event) {
           is EditEvent.EnteredName -> {
               _departamentoTitle.value = departamentoTitle.value.copy(
                   text = event.value
               )
           }
           is EditEvent.EnteredLastName -> {
               _departamentoDescription.value = departamentoDescription.value.copy(
                   text = event.value
               )
           }
           is EditEvent.EnteredAge -> {
               _departamentoCode.value = departamentoCode.value.copy(
                   text = event.value
               )
           }
           EditEvent.Insertdepartamento -> {
               viewModelScope.launch {
                   insertdepartamento(
                       Departamento(
                           title = departamentoTitle.value.text,
                           description = departamentoDescription.value.text,
                           code = departamentoCode.value.text,
                           imgUri = R.drawable.amazonas,
                           id = currentDepartamentoId
                       )
                   )
                   _eventFlow.emit(UiEvent.SaveDepartamento)
               }
           }
       }
    }

    sealed class UiEvent {
        object SaveDepartamento: UiEvent()
    }
}