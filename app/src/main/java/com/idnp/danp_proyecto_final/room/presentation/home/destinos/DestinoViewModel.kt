package com.idnp.danp_proyecto_final.room.presentation.home.destinos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idnp.danp_proyecto_final.room.domain.use_cases.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class DestinoViewModel @Inject constructor(
    private val deleteDestino: DeleteDestino,
    getDestinos: GetDestinos
): ViewModel() {

    private val _state = mutableStateOf(DestinoState())
    val state: State<DestinoState> = _state

    init {
        getDestinos().onEach { destinos ->
            _state.value = state.value.copy(
                destinos = destinos
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: DestinoEvent) {
        when (event) {
            is DestinoEvent.DeleteDestino -> {
                viewModelScope.launch {
                    deleteDestino(event.destino)
                }
            }
        }
    }
}