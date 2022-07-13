package com.idnp.danp_proyecto_final.room.presentation.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentDepartamentoId: Int? = null



    fun onEvent(event: EditEvent) {
       when (event) {
           is EditEvent.EnteredTitle -> {
               _departamentoTitle.value = departamentoTitle.value.copy(
                   text = event.value
               )
           }
           EditEvent.InsertDepartamento -> {
               viewModelScope.launch {
                   insertdepartamento(
                       Departamento(
                           id = currentDepartamentoId,
                           title = departamentoTitle.value.text,
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